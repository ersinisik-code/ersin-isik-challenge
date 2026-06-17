package com.camunda.challenge.model;

public record User(
        int id,
        String email,
        String firstName,
        String lastName
) {}