package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.http.Method.DELETE;
import static org.testng.Assert.*;

public class DeleteUser {

    @Test
    public void deleteUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String userId = "2";

        Response response = RestAssured.given().request(DELETE, "/users/" + userId);

        // Assertion 1: Check the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Response status code :: " + statusCode);
        assertEquals(statusCode, 204, "Unexpected status code");

        // Assertion 2: Check if the response body is empty
        String responseBody = response.getBody().asString();
        System.out.println("Response body :: " + responseBody);
        assertTrue(responseBody.isEmpty(), "Response body is not empty");
    }
}
