package com.camunda.challenge;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class RestClientTest {

    @Test
    public void testReqresApiAvailability() {
        try {
            // Reqres API'sinin ayakta olup olmadigini direkt test ediyoruz
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://reqres.in/api/users?page=1"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // API 200 OK donuyor mu?
            assertEquals(200, response.statusCode(), "API status code should be 200");
            
            // Donen cevabin icinde data var mi?
            assertTrue(response.body().contains("data"), "Response should contain data field");
            
        } catch (Exception e) {
            fail("API connection failed: " + e.getMessage());
        }
    }
}