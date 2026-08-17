package tests;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests {

    @Test(priority = 1)
    public void testCreateUser(){
        //prepare payload using POJO model
        User userPayload = new User("Leila", "QA Automation Engineer");

        //send POST request
        Response response = UserEndpoints.createUser(userPayload);

        //Print response payload to console for debugging
        response.then().log().all();


        //JSONPlaceholder returns HTTP 201 for POST
        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code 201 for POST request");

        //validate response body values
        Assert.assertEquals(response.jsonPath().getString("name"), userPayload.getName());
        Assert.assertEquals(response.jsonPath().getString("job"), userPayload.getJob());


        //Ensure the server generated a valid User ID
        Assert.assertNotNull(response.jsonPath().getString("id"), "User ID should not be null");

    }

    @Test(priority = 2)
    public void testGetSingleUser(){
        int userId =2;


        //Send GET request
        Response response = UserEndpoints.getUser(userId);


        //Print response details
        response.then().log().all();


        //Verify HTTP status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Expected code 200 for GET request");


        //Extract dynamic fields
        String actualEmail = response.jsonPath().getString("email");
        String actualName = response.jsonPath().getString("name");

        //Dynamic Assertions
        Assert.assertNotNull(actualName, "Name field should exist and not be null");
        Assert.assertNotNull(actualEmail, "Email field should exist and not be null");
        Assert.assertTrue(actualEmail.contains("@"), "Email should have a valid '@' format");
    }
}
