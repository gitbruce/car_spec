package com.bruce.car.entity

import com.bruce.base.BaseEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_factory")
class CarFactory : BaseEntity() {
    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    var brand: CarBrand? = null
    var brandName: String = ""

    //广汽丰田
    @Column(nullable = false)
    var name: String = ""

}