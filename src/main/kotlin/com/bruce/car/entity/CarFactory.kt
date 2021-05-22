package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_factory")
class CarFactory : PanacheEntityBase {
    @Id
    var id: Long? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id")
    var brand: CarBrand? = null
    var brandName: String = ""

    //广汽丰田
    @Column(nullable = false)
    var name: String = ""

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}