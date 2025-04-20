package API;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SampleAPITest {

    @Test
    public void testGetUsers() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .when()
            .get("/users?page=2")
            .then()
            .statusCode(200)
            .body("data.size()", greaterThan(0));
    }
}

