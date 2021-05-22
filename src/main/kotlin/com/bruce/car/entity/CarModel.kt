package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name="car_model")
class CarModel : PanacheEntity() {
    @ManyToOne(optional = false)
    @JoinColumn(name="brand_id")
    lateinit var brand: CarBrand
    lateinit var brandName: String

    @ManyToOne(optional = false)
    @JoinColumn(name="factory_id")
    lateinit var factory: CarFactory
    lateinit var factoryName: String

    //丰田-雷凌
    @Column(nullable = false)
    lateinit var name: String
    @Column(nullable = false)
    lateinit var seriesState: String
    var seriesOrder: Int = 0

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}