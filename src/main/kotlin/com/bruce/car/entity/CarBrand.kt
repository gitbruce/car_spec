package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import java.time.LocalDate
import javax.persistence.Cacheable
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Cacheable
@Table(name="car_brand")
class CarBrand : PanacheEntity() {
    companion object: PanacheCompanion<CarBrand> {
        fun findByName(name: String) = find("name", name).firstResult()
        fun deleteStefs() = delete("name", "Stef")
    }

    lateinit var name: String
    lateinit var birth: LocalDate
}