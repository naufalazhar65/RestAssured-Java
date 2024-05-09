package users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    private String baseUrl = "https://gorest.co.in/public/v2";
    private String bearerToken = "02a943da4b6b96018eb83cc86f19b89b48a1669fdf88944cb0563d3315a0948d";
    private int userId = 6893644;
    private String userEndpoint = "/users/" + userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void updateUserTest() {
        String name = "Naufal Az";
        String email = "naufak3@gmail.com";
        String gender = "male";
        String status = "active";

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
            .put(userEndpoint);

        response.then()
            .log().all()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON);
        
    }
}
