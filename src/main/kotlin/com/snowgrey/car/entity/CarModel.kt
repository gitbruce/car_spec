package com.snowgrey.car.entity

import com.snowgrey.base.BaseEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model")
class CarModel : BaseEntity() {
    companion object : PanacheCompanion<CarModel> {
        fun findByName(name: String) = CarModel.find("name", name).firstResult()
        fun deleteStefs() = CarModel.delete("name", "Stef")
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    var brand: CarBrand? = null
    var brandName: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "factory_id")
    var factory: CarFactory? = null
    var factoryName: String = ""

    //丰田-雷凌
    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false)
    var seriesState: String = ""
    var seriesOrder: Int = 0

}