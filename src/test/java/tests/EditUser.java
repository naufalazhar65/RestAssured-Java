package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.http.Method.PUT;
import static org.testng.Assert.*;

public class EditUser {

    @Test
    public void updateUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        String userId = "2";
        String name = "UpdatedNaufal";
        String job = "UpdatedTester";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\":\"" + name + "\",\"job\":\"" + job + "\"}")
                .request(PUT, "/users/" + userId);

        // Assertion 1: Check the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Response status code :: " + statusCode);
        assertEquals(statusCode, 200, "Unexpected status code");

        // Assertion 2: Check if the response body contains the updated user's name
        String responseBody = response.getBody().asString();
        System.out.println("Response body :: " + responseBody);
        assertTrue(responseBody.contains(name), "Response body doesn't contain the expected updated name");
    }
}
