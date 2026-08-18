package tests;

import endpoints.UserEndpoints;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTests {

    private int dynamicUserId;
    private User userPayload;

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


        dynamicUserId = response.jsonPath().getInt("id");
        //Ensure the server generated a valid User ID
        Assert.assertNotNull(dynamicUserId, "Generated ID should not be null");

    }

    @Test(priority = 2, dependsOnMethods = {"testCreateUser"})
    public void testGetSingleUser(){
        int targetId = (dynamicUserId > 10) ? 2 : dynamicUserId;


        //Send GET request
        Response response = UserEndpoints.getUser(targetId);


        //Print response details
        response.then().log().all();


        //Verify HTTP status code is 200 (OK)
        Assert.assertEquals(response.getStatusCode(), 200, "Expected code 200 for GET request");



        //Dynamic Assertions
        Assert.assertNotNull(response.jsonPath().getString("email"), "email field should exist and not be null");

    }

    @Test(priority = 3, dependsOnMethods = {"testCreateUser"})
    public void testUpdateUser(){
        int targetId = (dynamicUserId > 10) ? 2 : dynamicUserId;

        User updatePayload = new User("Leila Updated", "Senior QA Lead");

        Response response = UserEndpoints.updateUser(targetId, updatePayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertEquals(response.jsonPath().getString("name"), "Leila Updated");
        Assert.assertEquals(response.jsonPath().getString("job"), "Senior QA Lead");
    }

    @Test(priority = 4, dependsOnMethods = {"testCreateUser"})
    public void testDeleteUser(){
        int targetId = (dynamicUserId > 10) ? 2 : dynamicUserId;

        Response response = UserEndpoints.deleteUser(targetId);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected Status code 200 for deletion");
    }
}
