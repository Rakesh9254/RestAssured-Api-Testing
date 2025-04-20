package CodeStudio;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*; // Needed for given(), when(), then()
import static org.hamcrest.Matchers.*;      // Needed for equalTo(), greaterThan()

public class FirstGetRequest {

    @Test
    void getRequest() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");

        System.out.println("Full Response:\n" + response.asString());
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("Time: " + response.getTime() + " ms");
        System.out.println("Content Type: " + response.contentType());

        // Assert: response as string should match body as string (same thing)
        Assert.assertEquals(response.asString(), response.getBody().asString());
    }

    @Test
    void GetRequest01() {
        // BDD-style: Given - When - Then
        RestAssured.baseURI = "https://reqres.in/api";

        given()
            .queryParam("page", "2") // Given
        .when()
            .get("/users")           // When
        .then()
            .statusCode(200) ;        // Then
            
    }
    @Test
    void test03() {
    	JsonObject object = new JsonObject();
    	object.addProperty("name", "Rakesh");
    	object.addProperty("job", "QA");
    	
    	RestAssured .baseURI = "https://reqres.in/api";
    	given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON).
			body(object.toString()).
			when()
			.post("/users")
			.then()
			.statusCode(201)
			.log().all();
    }
}
