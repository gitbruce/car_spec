package com.snowgrey.car.entity

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
    var country: Country? = null
    var countryName: String = ""

    //丰田
    @Column(nullable = false)
    var name: String = ""
    var logo: String = ""

}