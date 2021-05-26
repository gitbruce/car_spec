package com.snowgrey.car.entity

import com.snowgrey.base.BaseEntity
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

@Entity
@Cacheable
@Table(name = "country")
class Country : BaseEntity()  {

    companion object : PanacheCompanion<Country> {
        fun findByName(name: String) = Country.find("name", name).firstResult()
        fun deleteStefs() = Country.delete("name", "Stef")
    }

    @Column(length = 40, unique = true)
    var name: String = ""

}

