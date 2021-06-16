package com.snowgrey.car.service

import javax.enterprise.context.ApplicationScoped

interface BaseService<T> {
    fun getAll(): List<T>
    fun getSingle(id: Long): T?
    fun create(item: T): T
    fun update(id: Long, item: T): T?
    fun delete(id: Long)
}