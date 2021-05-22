package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model_year")
class CarModelYear : PanacheEntityBase {
    @Id
    var id: Long? = null

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

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}