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
class CarModelYearTest {

    @Test
    @Order(1)
    fun testListAll() {
        given()
            .`when`()["/modelYears/"]
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
                 {"id": 1001, "brand": {"id": 3},"brandName": "丰田", "factory":{"id":1001}, "factoryName":"一汽丰田",
                 "model":{"id":1001}, "modelName":"卡罗拉", "name": "丰田-卡罗拉-2021款"}
            """.trimIndent())
            .contentType("application/json")
            .post("/modelYears/")
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
            .`when`()["/modelYears/1001"]
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
                {"brand": {"id": 3},"brandName": "丰田", "factory":{"id":1001}, "factoryName":"一汽丰田",
                 "model":{"id":1001}, "modelName":"卡罗拉", "name": "丰田-卡罗拉-2021款2"}
            """.trimIndent())
            .contentType("application/json")
            .put("/modelYears/1001")
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
            .delete("/modelYears/1001")
            .then()
            .statusCode(204)
    }

//    @Test
//    @Order(6)
    fun testSelectNotExist() {
        given()
            .`when`()["/modelYears/1001"]
            .then()
            .statusCode(404)
    }

}