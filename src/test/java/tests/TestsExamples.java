package tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import static io.restassured.http.Method.GET;
import static org.testng.Assert.*;

public class TestsExamples {

    @Test
    public void getWeatherStatus() {
        RestAssured.baseURI = "http://api.weatherapi.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/v1/current.json?key=aca062e1bb9f4d7694a95717231112&q=Indonesia&aqi=no");

        // Assertion 1: Check the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Response status code :: " + statusCode);
        assertEquals(statusCode, 200, "Correct Status Code");

        // Assertion 2: Check if the response body is not empty
        String responseBody = response.getBody().asString();
        System.out.println("Response body :: " + responseBody);
        assertNotNull(responseBody, "Response body is location");

    }
    
    @Test
    public void getWeatherStatus1() {
        RestAssured.baseURI = "http://api.weatherapi.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/v1/current.json?key=aca062e1bb9f4d7694a95717231112&q=1&aqi=no");

        // Assertion 1: Check the response status code
        int statusCode = response.getStatusCode();
        System.out.println("Response status code :: " + statusCode);
        assertEquals(statusCode, 400, "Correct Status Code");

        // Assertion 2: Check if the response body is not empty
        String responseBody = response.getBody().asString();
        System.out.println("Response body :: " + responseBody);
        assertNotNull(responseBody, "Response body is empty");

    }
    
    
}
