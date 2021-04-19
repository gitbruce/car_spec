package com.bruce.car.entity

import java.util.*
import javax.persistence.*

@Entity
@Cacheable
@Table(name="country")
@NamedQuery(name = Country.FIND_ALL, query = "SELECT f FROM Country f ORDER BY f.name")
class Country {
    companion object {
        const val FIND_ALL = "Country.findAll"
    }

    @Id
    @SequenceGenerator(
        name = "countrySequence",
        sequenceName = "known_country_id_seq",
        allocationSize = 1,
        initialValue = 10
    )
    @GeneratedValue(generator = "countrySequence")
    var id: Int = 0

    @Column(length = 40, unique = true)
    lateinit var name: String

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var lastUpdate: Date? = null

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    var createDate: Date? = null
}

