package com.bruce.car.entity

import com.bruce.base.BaseEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model_year")
class CarModelYear : BaseEntity() {

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