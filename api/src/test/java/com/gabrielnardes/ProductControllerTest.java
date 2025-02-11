package com.gabrielnardes;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Disabled("TODO: application.properties uses 'postgres' instead of 'localhost'")
@QuarkusTest
class ProductControllerTest {
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/api/v1/product")
          .then()
             .statusCode(200);
    }

}