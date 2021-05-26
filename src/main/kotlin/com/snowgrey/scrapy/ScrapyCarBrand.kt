package com.snowgrey.scrapy

import com.fasterxml.jackson.databind.ObjectMapper
import com.snowgrey.car.entity.CarBrand
import com.fasterxml.jackson.databind.JsonNode




class ScrapyCarBrand : ScrapyBase() {
    override fun getUrl(): String {
        return "/ashx/AjaxIndexCarFind.ashx?type=11"
    }

    override fun tranverseEntity(): List<CarBrand> {
        var json = retrieveJson()
        val mapper = ObjectMapper()
        return listOf()
//        val rootNode: JsonNode = mapper.readTree(json)
    }

}