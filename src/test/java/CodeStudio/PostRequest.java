package CodeStudio;

import static io.restassured.RestAssured.given;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.testng.annotations.Test;

public class PostRequest {

    @Test
    public void createUserTest() {

        // Create JSON payload
        JsonObject object = new JsonObject();
        object.addProperty("name", "Rakesh");
        object.addProperty("job", "QA");
        object.addProperty("id", "12345");
        object.addProperty("salary", 250000);
        

        // Set Base URI
        RestAssured.baseURI = "https://reqres.in/api";

        // Send POST request and validate response
        given()
            .header("Content-Type", "application/json")
            .contentType(ContentType.JSON)
            .body(object.toString())
        .when()
            .post("/users")
        .then()
            .statusCode(201)  // Check status code
            .log().all();     // Print full response
    }
    @Test
    public void createUserTest1() {
    	JsonObject object = new JsonObject();
    	object.addProperty("name", "Rakesh");
    	object.addProperty("job", "QA");
    		object.addProperty("id", "12345");
    		object.addProperty("salary", 250000);
    		RestAssured .baseURI = "https://reqres.in/api";
    		given()
				.header("Content-Type", "application/json")
				.contentType(ContentType.JSON).
				body(object.toString()).
				when()
				.put("/users")
				.then()
				.statusCode(404)
				.log().all();
    }
    
    
}
