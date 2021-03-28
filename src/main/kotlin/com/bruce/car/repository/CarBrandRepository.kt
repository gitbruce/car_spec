package com.bruce.car.repository

import com.bruce.car.entity.CarBrand
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository

class CarBrandRepository : PanacheRepository<CarBrand> {
    fun findByName(name: String) = find("name", name).firstResult()
    fun deleteStefs() = delete("name", "Stef")
}