package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import models.User;
import static io.restassured.RestAssured.given;

public class UserEndpoints {

    public static final String BASE_URL= "https://jsonplaceholder.typicode.com";

    public static Response createUser(User payload){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)

                .when()
                .post(BASE_URL + "/users");

    }
    public static Response getUser(int userId){
        return given()
                .pathParam("id", userId)
                .when()
                .get(BASE_URL + "/users/{id}");
    }
}
