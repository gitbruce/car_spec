package com.bruce.car.repository

import com.bruce.car.entity.Country
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository

class CountryRepository : PanacheRepository<Country> {
}