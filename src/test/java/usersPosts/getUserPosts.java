package usersPosts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.File;

public class getUserPosts {

    private String baseUrl = "https://gorest.co.in";
    private String bearerToken = "02a943da4b6b96018eb83cc86f19b89b48a1669fdf88944cb0563d3315a0948d";
    private int userId = 6893644;
    private String userPostsEndpoint = "/public/v2/users/" + userId + "/posts";
    private String schemaPath = "src/test/resources/GetUserPostsSchema.json";


    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void createUserPostTest() {
    	

        Response response = given()
            .header("Authorization", "Bearer " + bearerToken)
            .contentType(ContentType.JSON)
            .when()
            .get(userPostsEndpoint);

        response.then()
            .log().all()
            .assertThat()
            .statusCode(200)
        	.body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));    

    }
}
