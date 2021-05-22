package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_model_type")
class CarModelType : PanacheEntityBase {
    @Id
    var id: Long? = null

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

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}