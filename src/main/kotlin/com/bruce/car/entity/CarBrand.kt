package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "car_brand")
class CarBrand : PanacheEntityBase {
    companion object : PanacheCompanion<CarBrand> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun deleteStefs() = delete("name", "Stef")
    }

    @Id
    var id: Long? = null

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    var country: Country? = null
    var countryName: String = ""

    //丰田
    @Column(nullable = false)
    var name: String = ""
    var logo: String = ""

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}