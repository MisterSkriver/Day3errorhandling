package data;

import org.junit.Test;
import static org.junit.Assert.*;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;

public class QuoteResourceIntegrationTest {
    
    public QuoteResourceIntegrationTest() {
    }

    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/61Rest";
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @Test
    public void serverIsRunning() {
        given().
                when().get().
                then().
                statusCode(200);
    }
    
    @Test
    public void nonExistingQuote() {
        given().when().get("/api/quote/100").then().statusCode(404);
    }
    
    @Test
    public void existingQuote() {
        given().when().get("/api/quote/1").then().statusCode(200);
    }
    
}
