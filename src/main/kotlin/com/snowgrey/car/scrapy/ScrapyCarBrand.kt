package com.snowgrey.car.scrapy

import com.snowgrey.car.pojo.CarBrandPojo
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ScrapyCarBrand : SrapyBase<CarBrandPojo>() {
    override fun getPath(): String {
//        return "/ashx/AjaxIndexCarFind.ashx?type=11"
        return "C:/src/lab/car_spec/data/demo.json"
    }

    override fun getMode(): String {
        return "File"
    }

    override fun getStructure(): String {
        return "result.branditems"
    }


}