package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name="car_factory")
class CarFactory : PanacheEntity() {
    @ManyToOne(optional = false)
    @JoinColumn(name="brand_id")
    lateinit var brand : CarBrand
    lateinit var brandName: String

    //广汽丰田
    @Column(nullable = false)
    lateinit var name : String
}