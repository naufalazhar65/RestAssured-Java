package users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.File;

public class CreateUser {

    private String baseUrl = "https://gorest.co.in/public/v2";
    private String bearerToken = "02a943da4b6b96018eb83cc86f19b89b48a1669fdf88944cb0563d3315a0948d";
    private String userEndpoint = "/users";
    private String schemaPath = "src/test/resources/userSchema.json";


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void createUserTest() {
        String name = "naufal";
        String email = "naufal87@gmail.com";
        String gender = "male";
        String status = "inactive";

        String requestBody = "{"
                + "\"name\": \"" + name + "\","
                + "\"email\": \"" + email + "\","
                + "\"gender\": \"" + gender + "\","
                + "\"status\": \"" + status + "\""
                + "}";

        Response response = given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post(userEndpoint);

        response.then()
            .log().all()
            .assertThat()
            .statusCode(201)
            .contentType(ContentType.JSON)
        	.body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));

        
    }
}
