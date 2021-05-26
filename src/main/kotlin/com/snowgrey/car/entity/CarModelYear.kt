package com.snowgrey.car.entity

import com.snowgrey.base.BaseEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model_year")
class CarModelYear : BaseEntity() {
    companion object : PanacheCompanion<CarModelYear> {
        fun findByName(name: String) = CarModelYear.find("name", name).firstResult()
        fun deleteStefs() = CarModelYear.delete("name", "Stef")
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    lateinit var brand: CarBrand
    lateinit var brandName: String

    @ManyToOne(optional = false)
    @JoinColumn(name = "factory_id")
    var factory: CarFactory? = null
    var factoryName: String = ""

    @ManyToOne(optional = true)
    @JoinColumn(name = "model_id")
    var model: CarModel? = null
    var modelName: String = ""

    //丰田-雷凌-2021款
    @Column(nullable = false)
    var name: String = ""

}