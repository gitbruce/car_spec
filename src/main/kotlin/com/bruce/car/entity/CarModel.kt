package com.bruce.car.entity

import com.bruce.base.BaseEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model")
class CarModel : BaseEntity() {

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