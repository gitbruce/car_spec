package scrapy

import com.snowgrey.car.pojo.CarBrandPojo
import com.snowgrey.car.scrapy.ScrapyCarBrand
import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test

@QuarkusTest
class JsonReaderTest {

    @Test
    fun testCarBrandFile() {
        val crapyCarBrand  = ScrapyCarBrand()
        crapyCarBrand.parseJson(CarBrandPojo::class)
    }

}