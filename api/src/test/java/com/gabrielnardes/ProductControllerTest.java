package com.gabrielnardes;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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