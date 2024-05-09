package users;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File; 

import static io.restassured.RestAssured.given;

public class GetUser {

    private String baseUrl = "https://gorest.co.in/public/v2";
    private String bearerToken = "02a943da4b6b96018eb83cc86f19b89b48a1669fdf88944cb0563d3315a0948d";
    private int userId = 6893644;
    private String userEndpoint = "/users/" + userId;
    private String schemaPath = "src/test/resources/userSchema.json";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void getUserTest() {
        Response response = given()
            .header("Authorization", "Bearer " + bearerToken)
            .when()
            .get(userEndpoint);

        response.then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
        System.out.println(response.asString());

    }

}
