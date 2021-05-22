package car

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test


import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNot

@QuarkusTest
class CountryTest {

    @Test
    fun testListAllCountries() {
        //List all, should have all 3 Countries the database has initially:
        given()
            .`when`()["/countries/"]
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("Thailand"),
                CoreMatchers.containsString("China"),
                CoreMatchers.containsString("Japan"),
            )

        // Update Cherry to China
        given()
            .`when`()
            .body("{\"name\" : \"Singapore\"}")
            .contentType("application/json")
            .put("/countries/1")
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("\"id\":"),
                CoreMatchers.containsString("\"name\":\"Singapore\"")
            )

        //List all, China should've replaced Cherry:
        given()
            .`when`()["/countries/"]
            .then()
            .statusCode(200)
            .body(
                IsNot.not(CoreMatchers.containsString("Thailand")),
                CoreMatchers.containsString("Japan"),
                CoreMatchers.containsString("China"),
                CoreMatchers.containsString("Singapore")
            )

        //Delete China:
        given()
            .`when`()
            .delete("/countries/1")
            .then()
            .statusCode(204)

        //List all, China should be missing now:
        given()
            .`when`()["/countries/"]
            .then()
            .statusCode(200)
            .body(
                IsNot.not(CoreMatchers.containsString("Thailand")),
                CoreMatchers.containsString("Japan"),
                CoreMatchers.containsString("China")
            )

        //Create the Singapore:
        given()
            .`when`()
            .body("{\"name\" : \"Singapore\"}")
            .contentType("application/json")
            .post("/countries/")
            .then()
            .statusCode(201)
            .body(
                CoreMatchers.containsString("\"id\":"),
                CoreMatchers.containsString("\"name\":\"Singapore\"")
            )

        //List all, China should be still missing now:
        given()
            .`when`()["/countries/"]
            .then()
            .statusCode(200)
            .body(
                IsNot.not(CoreMatchers.containsString("Thailand")),
                CoreMatchers.containsString("Japan"),
                CoreMatchers.containsString("China"),
                CoreMatchers.containsString("Singapore")
            )
    }
}