package com.snowgrey.car.pojo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.snowgrey.base.BasePojo
import com.snowgrey.car.entity.CarBrand
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion

@JsonIgnoreProperties(ignoreUnknown = true)
data class CarBrandPojo (
    var id: Long,
    @JsonProperty("countryid")
    var countryId: String,
    @JsonProperty("country")
    var countryName: String,
    var name: String,
    var logo: String
        )