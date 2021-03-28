package car

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test

import java.util.UUID

import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`

@QuarkusTest
class CountryTest {
    @Test
    fun testHelloEndpoint() {
        given()
                .`when`().get("/hello")
                .then()
                .statusCode(200)
                .body(`is`("hello"))
    }

}