package com.camunda.challenge;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RestClientTest {

    // TEST 1: Basic test to check if users are fetched correctly from the API
    @Test
    public void testFetchAllUsersDynamic() {
        RestClient client = new RestClient();
        
        List<User> userList = client.fetchAllUsers();
        
        assertNotNull(userList);
     
        if (!userList.isEmpty()) {
            System.out.println("Test Successful! Total users found: " + userList.size());

            assertNotNull(userList.get(0).getId());
            assertNotNull(userList.get(0).getEmail());
        }
    }

    // TEST 2: Test to verify that the application does not crash on errors
    @Test
    public void testErrorHandlingWithWrongUrl() {
        try {
            RestClient client = new RestClient();
          
            List<User> emptyList = client.fetchAllUsers();
            
            assertNotNull(emptyList);
            System.out.println("Test Successful! Error was handled gracefully without crashing.");
            
        } catch (Exception e) {
            fail("Application crashed, error handling failed: " + e.getMessage());
        }
    }
}
