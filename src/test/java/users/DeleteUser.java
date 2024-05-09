package users;

import io.restassured.RestAssured;
import io.restassured.response.Response; // Import untuk Response
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser {

    private String baseUrl = "https://gorest.co.in/public/v2";
    private String bearerToken = "02a943da4b6b96018eb83cc86f19b89b48a1669fdf88944cb0563d3315a0948d";
    private int userId = 6898519;
    private String userEndpoint = "/users/" + userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    @Test
    public void deleteUserTest() {
        Response response = given()
            .header("Authorization", "Bearer " + bearerToken)
            .when()
            .delete(userEndpoint);

        response.then()
            .log().all()
            .assertThat()
            .statusCode(204);
        
    }
}
