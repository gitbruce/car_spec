package car

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class CarBrandTest {

    @Test
    @Order(1)
    fun testListAll() {
        given()
            .`when`()["/brands/"]
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("\"id\":")
            )
    }

    @Test
    @Order(2)
    fun testCreate() {
        given()
            .`when`()
            .body("""
                 {"id":1001, "country": {"id": 3},"countryName": "日本","name": "本田1","logo": "http://www.baidu.com"}
            """.trimIndent())
            .contentType("application/json")
            .post("/brands/")
            .then()
            .statusCode(201)
            .body(
                CoreMatchers.containsString("\"id\":"),
            )
    }

    @Test
    @Order(3)
    fun testSelectExist() {
        given()
            .`when`()["/brands/1001"]
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("\"id\":")
            )
    }

    @Test
    @Order(4)
    fun testUpdate() {
        given()
            .`when`()
            .body("""
                {"country": {"id": 3},"countryName": "中国","name": "本田2","logo": "http://www.baidu2.com"}
            """.trimIndent())
            .contentType("application/json")
            .put("/brands/1001")
            .then()
            .statusCode(200)
            .body(
                CoreMatchers.containsString("\"id\":"),
            )
    }

//    @Test
//    @Order(5)
    fun testDelete() {
        given()
            .`when`()
            .delete("/brands/1001")
            .then()
            .statusCode(204)
    }

//    @Test
//    @Order(6)
    fun testSelectNotExist() {
        given()
            .`when`()["/brands/1001"]
            .then()
            .statusCode(404)
    }

}