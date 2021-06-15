package com.snowgrey.car.entity

import com.fasterxml.jackson.annotation.JsonProperty
import com.snowgrey.base.BaseEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_brand")
class CarBrand : BaseEntity() {
    companion object : PanacheCompanion<CarBrand> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun deleteStefs() = delete("name", "Stef")
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    @JsonProperty("countryid")
    var country: Country? = null
    @JsonProperty("country")
    var countryName: String = ""

    //丰田
    @Column(nullable = false)
    @JsonProperty
    var name: String = ""
    @JsonProperty
    var logo: String = ""

}