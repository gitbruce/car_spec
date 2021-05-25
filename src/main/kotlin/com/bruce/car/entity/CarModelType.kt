package com.bruce.car.entity

import com.bruce.base.BaseEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model_type")
class CarModelType : BaseEntity() {

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    var brand: CarBrand? = null
    var brandName: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "factory_id")
    var factory: CarFactory? = null
    var factoryName: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id")
    var model: CarModel? = null
    var modelName: String = ""

    @ManyToOne(optional = false)
    @JoinColumn(name = "model_year_id")
    var modelYear: CarModelYear? = null

    //丰田-雷凌 / 2021款 / 2021款 TNGA 1.5L CVT进取版
    @Column(nullable = false)
    var name: String = ""
    var state: String = ""

    var minPrice: Double = 0.0
    var maxPrice: Double = 0.0

}