package com.bruce.car.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "country")
class Country : PanacheEntityBase {
    @Id
    var id: Long? = null

    companion object : PanacheCompanion<Country> {
        fun findByName(name: String) = Country.find("name", name).firstResult()
        fun deleteStefs() = Country.delete("name", "Stef")
    }

    @Column(length = 40, unique = true)
    var name: String = ""

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date = Date()

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date = Date()
}

