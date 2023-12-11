package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.http.Method.GET;
import static org.testng.Assert.*;

public class GetUser {

    @Test
    public void getUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        Response response = RestAssured.given().request(GET, "/users");

        // Assertion 1: Check the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Response status code :: " + statusCode);
        assertEquals(statusCode, 200, "Unexpected status code");

        // Assertion 2: Check if the response body is not empty
        String responseBody = response.getBody().asString();
        System.out.println("Response body :: " + responseBody);
        assertNotNull(responseBody, "Response body is empty");

       
    }
}
