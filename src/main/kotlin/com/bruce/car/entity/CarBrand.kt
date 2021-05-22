package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name="car_brand")
class CarBrand : PanacheEntity() {
    companion object: PanacheCompanion<CarBrand> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun deleteStefs() = delete("name", "Stef")
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="country_id")
    lateinit var country: Country
    lateinit var countryName: String

    //丰田
    @Column(nullable = false)
    lateinit var name: String
    lateinit var logo: String

}