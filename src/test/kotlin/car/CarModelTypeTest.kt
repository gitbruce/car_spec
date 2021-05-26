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
class CarModelYearTypeTest {

    @Test
    @Order(1)
    fun testListAll() {
        given()
            .`when`()["/modelTypes/"]
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
                 "model":{"id":1001}, "modelName":"卡罗拉", "modelYear":{"id":1001}, 
                 "name": "丰田-卡罗拉 / 2021款 / 2021款 TNGA 1.5L CVT进取版", "state": "Y", 
                 "minPrice": 90000, "maxPrice": 120000}
            """.trimIndent())
            .contentType("application/json")
            .post("/modelTypes/")
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
            .`when`()["/modelTypes/1001"]
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
                {"id": 1001, "brand": {"id": 3},"brandName": "丰田", "factory":{"id":1001}, "factoryName":"一汽丰田",
                 "model":{"id":1001}, "modelName":"卡罗拉", "modelYear":{"id":1001}, 
                 "name": "丰田-卡罗拉 / 2021款 / 2021款 TNGA 1.5L CVT进取版2", "state": "Y", 
                 "minPrice": 90000, "maxPrice": 120000}
            """.trimIndent())
            .contentType("application/json")
            .put("/modelTypes/1000")
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
            .delete("/modelTypes/1001")
            .then()
            .statusCode(204)
    }

//    @Test
//    @Order(6)
    fun testSelectNotExist() {
        given()
            .`when`()["/modelTypes/1001"]
            .then()
            .statusCode(404)
    }

}