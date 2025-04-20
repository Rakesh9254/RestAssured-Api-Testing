package API;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }

    @Test
    public void getUsersList() {
        given()
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            .body("data.size()", greaterThan(0));
    }
}
