package com.snowgrey.car.service

abstract class BaseService<T> {
    abstract fun get(): List<T>
    abstract fun getSingle(id: Long): T
    abstract fun create(item: T): T
    abstract fun update(id: Long, item: T): T
    abstract fun delete(id: Long)
}