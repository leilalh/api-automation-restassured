package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import models.User;
import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public static final String BASE_URL= "https://jsonplaceholder.typicode.com";


    //create User (POST)
    public static Response createUser(User payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(BASE_URL + "/users");

    }

    //Read User (GET)
    public static Response getUser(int userId){
        return given()
                .pathParam("id", userId)
                .when()
                .get(BASE_URL + "/users/{id}");
    }


    //Update User (PUT)
    public static Response updateUser(int userId, User payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", userId)
                .body(payload)
                .when()
                .put(BASE_URL + "/users/{id}");

    }

    public static Response deleteUser(int unserId){
        return given()
                .pathParam("id", unserId)
                .when()
                .delete(BASE_URL + "/users/{id}");
    }
}
