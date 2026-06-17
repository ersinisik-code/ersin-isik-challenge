package com.camunda.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class ApiResponse {

    private int page;

    @JsonProperty("total_pages")
    private int totalPages;

    private List<UserData> data;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<UserData> getData() {
        return data;
    }

    public static class UserData {

        private int id;

        private String email;

        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }
}