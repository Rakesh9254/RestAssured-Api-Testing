package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import config.ConfigReader;

public class ApiTests {

    String baseUrl = "https://reqres.in/api";

    // 1. Get all users (GET)
    @Test
    public void testGetAllUsers() {
        RestAssured
            .given()
            .when()
            .get(baseUrl + "/users?page=2")
            .then()
            .statusCode(200)
            .log().all();
    }

    // 2. Get single user (GET)
    @Test
    public void testGetSingleUser() {
        RestAssured
            .given()
            .when()
            .get(baseUrl + "/users/2")
            .then()
            .statusCode(200)
            .body("data.id", equalTo(2));
    }

    // 3. Get non-existing user (Negative Test)
    @Test
    public void testUserNotFound() {
        RestAssured
            .given()
            .when()
            .get(baseUrl + "/users/23")
            .then()
            .statusCode(404);
    }

    // 4. Create new user (POST)
    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John\", \"job\": \"QA\" }";

        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post(baseUrl + "/users")
            .then()
            .statusCode(201)
            .body("name", equalTo("John"));
    }

    // 5. Update user (PUT)
    @Test
    public void testUpdateUser() {
        String requestBody = "{ \"name\": \"Ravi\", \"job\": \"Tester\" }";

        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .put(baseUrl + "/users/2")
            .then()
            .statusCode(200)
            .body("job", equalTo("Tester"));
    }

    // 6. Update user (PATCH)
    @Test
    public void testPatchUser() {
        String requestBody = "{ \"job\": \"Manager\" }";

        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .patch(baseUrl + "/users/2")
            .then()
            .statusCode(200)
            .body("job", equalTo("Manager"));
    }

    // 7. Delete user (DELETE)
    @Test
    public void testDeleteUser() {
        RestAssured
            .given()
            .when()
            .delete(baseUrl + "/users/2")
            .then()
            .statusCode(204); // No content
    }

    // 8. Register user successfully
    @Test
    public void testRegisterSuccessful() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post(baseUrl + "/register")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("token", notNullValue());
    }

    // 9. Register user - Missing password (Negative)
    @Test
    public void testRegisterUnsuccessful() {
        String requestBody = "{ \"email\": \"sydney@fife\" }";

        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post(baseUrl + "/register")
            .then()
            .statusCode(400)
            .body("error", equalTo("Missing password"));
    }

    // 10. Delay Response (Test response time)
    @Test
    public void testDelayedResponse() {
        long responseTime = RestAssured
            .given()
            .when()
            .get(baseUrl + "/users?delay=3")
            .then()
            .statusCode(200)
            .extract()
            .time();

        System.out.println("Response time: " + responseTime + " ms");
    }
}
