package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity
@Cacheable
@Table(name="country")
class Country : PanacheEntity() {
    @Column(length = 40, unique = true)
    lateinit var name: String

    @Id
    var countryId: Int = 0
}