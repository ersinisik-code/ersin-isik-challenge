package com.camunda.challenge;

import com.camunda.challenge.client.ReqresClient;
import com.camunda.challenge.model.User;
import com.camunda.challenge.util.CsvExporter;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ReqresClient client =
                new ReqresClient();

        List<User> users =
                client.fetchAllUsers();

        users.forEach(user ->
                System.out.println(
                        user.firstName()
                                + " "
                                + user.lastName()
                )
        );

        new CsvExporter()
                .export(
                        users,
                        "output/users.csv"
                );

        System.out.println(
                "CSV file exported successfully."
        );
    }
}