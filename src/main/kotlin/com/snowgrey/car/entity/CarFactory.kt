package com.snowgrey.car.entity

import com.snowgrey.base.BaseEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_factory")
class CarFactory : BaseEntity() {
    companion object : PanacheCompanion<CarFactory> {
        fun findByName(name: String) = CarFactory.find("name", name).firstResult()
        fun deleteStefs() = CarFactory.delete("name", "Stef")
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    var brand: CarBrand? = null
    var brandName: String = ""

    //广汽丰田
    @Column(nullable = false)
    var name: String = ""

}