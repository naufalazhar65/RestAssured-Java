package usersTodos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.time.LocalDateTime; 

public class CreateUserTodos {

    private String baseUrl = "https://gorest.co.in";
    private String bearerToken = "02a943da4b6b96018eb83cc86f19b89b48a1669fdf88944cb0563d3315a0948d";
    private int userId = 6893644;
    private String userTodosEndpoint = "/public/v2/users/" + userId + "/todos";
    private String schemaPath = "src/test/resources/userTodosSchema.json";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void createUserTodoTest() {
        String currentTime = LocalDateTime.now().toString();
        
        String requestBody = "{" +
                "\"title\": \"Lunas.\"," +
                "\"due_on\": \"" + currentTime + "\"," + 
                "\"status\": \"pending\"" +
                "}";

        Response response = given()
                .header("Authorization", "Bearer " + bearerToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(userTodosEndpoint);

        response.then()
            .log().all()
            .assertThat()
            .statusCode(201)
            .contentType(ContentType.JSON)
            .body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));    
    }
}
