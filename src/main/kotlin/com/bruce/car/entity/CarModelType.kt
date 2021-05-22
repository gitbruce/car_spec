package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name="car_model_type")
class CarModelType : PanacheEntity() {
    @ManyToOne(optional = false)
    @JoinColumn(name="brand_id")
    lateinit var brand: CarBrand
    lateinit var brandName: String

    @ManyToOne(optional = false)
    @JoinColumn(name="factory_id")
    lateinit var factory: CarFactory
    lateinit var factoryName: String

    @ManyToOne(optional = false)
    @JoinColumn(name="model_id")
    lateinit var model: CarModel
    lateinit var modelName: String

    @ManyToOne(optional = false)
    @JoinColumn(name="model_year_id")
    lateinit var modelYear: CarModelYear

    //丰田-雷凌 / 2021款 / 2021款 TNGA 1.5L CVT进取版
    @Column(nullable = false)
    lateinit var name: String
    lateinit var state: String

    var minPrice: Double = 0.0
    var maxPrice: Double = 0.0

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}