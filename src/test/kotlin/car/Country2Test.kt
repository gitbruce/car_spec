package car

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNot
import org.junit.jupiter.api.Test

@QuarkusTest
class Country2Test {

    @Test
    fun testListAllCountries() {
        //List all, should have all 3 Countries the database has initially:
        RestAssured.given()
            .`when`()["/countries2/"]
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("Thailand"),
                CoreMatchers.containsString("China"),
                CoreMatchers.containsString("Japan"),
            )

        // Update Cherry to China
        RestAssured.given()
            .`when`()
            .body("{\"name\" : \"Singapore\"}")
            .contentType("application/json")
            .put("/countries2/1")
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("\"id\":"),
                CoreMatchers.containsString("\"name\":\"Singapore\"")
            )

        //List all, China should've replaced Cherry:
        RestAssured.given()
            .`when`()["/countries2/"]
            .then()
            .statusCode(200)
            .body(
                IsNot.not(CoreMatchers.containsString("Thailand")),
                CoreMatchers.containsString("Japan"),
                CoreMatchers.containsString("China"),
                CoreMatchers.containsString("Singapore")
            )

        //Delete China:
        RestAssured.given()
            .`when`()
            .delete("/countries2/1")
            .then()
            .statusCode(204)

        //List all, China should be missing now:
        RestAssured.given()
            .`when`()["/countries2/"]
            .then()
            .statusCode(200)
            .body(
                IsNot.not(CoreMatchers.containsString("Thailand")),
                CoreMatchers.containsString("Japan"),
                CoreMatchers.containsString("China")
            )

        //Create the Singapore:
        RestAssured.given()
            .`when`()
            .body("{\"name\" : \"Singapore\"}")
            .contentType("application/json")
            .post("/countries2/")
            .then()
            .statusCode(201)
            .body(
                CoreMatchers.containsString("\"id\":"),
                CoreMatchers.containsString("\"name\":\"Singapore\"")
            )

        //List all, China should be still missing now:
        RestAssured.given()
            .`when`()["/countries2/"]
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