package com.camunda.challenge.client;

import com.camunda.challenge.model.ApiResponse;
import com.camunda.challenge.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.ArrayList;
import java.util.List;

public class ReqresClient {

    private static final String BASE_URL =
            "https://reqres.in/api/users?page=";

    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public ReqresClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public List<User> fetchAllUsers() {

        List<User> users = new ArrayList<>();

        int page = 1;
        int totalPages;

        try {

            do {

                HttpRequest request =
                        HttpRequest.newBuilder()
                                .uri(URI.create(BASE_URL + page))
                                .GET()
                                .build();

                HttpResponse<String> response =
                        httpClient.send(
                                request,
                                HttpResponse.BodyHandlers.ofString()
                        );

                if (response.statusCode() != 200) {
                    throw new RuntimeException(
                            "API returned status "
                                    + response.statusCode()
                    );
                }

                ApiResponse apiResponse =
                        mapper.readValue(
                                response.body(),
                                ApiResponse.class
                        );

                totalPages = apiResponse.getTotalPages();

                apiResponse.getData().forEach(u ->
                        users.add(
                                new User(
                                        u.getId(),
                                        u.getEmail(),
                                        u.getFirstName(),
                                        u.getLastName()
                                )
                        )
                );

                page++;

            } while (page <= totalPages);

            return users;

        } catch (IOException | InterruptedException e) {

            throw new RuntimeException(
                    "Unable to fetch users",
                    e
            );
        }
    }
}