package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.http.Method.POST;
import static org.testng.Assert.*;

public class CreateUser {

    @Test
    public void createUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String name = "Naufal";
        String job = "Tester";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"" + name + "\",\"job\":\"" + job + "\"}")
                .request(POST, "/users");

        // Assertion 1: Check the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Response status code :: " + statusCode);
        assertEquals(statusCode, 201, "Unexpected status code");

        // Assertion 2: Check if the response body contains the created user's name
        String responseBody = response.getBody().asString();
        System.out.println("Response body :: " + responseBody);
        assertTrue(responseBody.contains(name), "Response body doesn't contain the expected name");
    }
}
