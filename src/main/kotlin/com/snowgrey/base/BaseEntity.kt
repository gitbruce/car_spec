package com.snowgrey.base

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity: PanacheEntityBase {
    @Id
    var id: Long? = null

    @Column(updatable = false)
    @CreationTimestamp
    var createTime: Date = Date()

    @UpdateTimestamp
    var updateTime: Date = Date()
}