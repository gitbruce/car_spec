package com.snowgrey.car.service

import com.snowgrey.car.entity.Country
import com.snowgrey.util.SnowFlake
import io.quarkus.panache.common.Sort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CountryService : BaseService<Country> {
    override fun getAll(): List<Country> {
        return Country.listAll(Sort.by("name"))
    }

    override fun getSingle(id: Long): Country? {
        return Country.findById(id)
    }

    override fun create(item: Country): Country {
        if (item.id == null) {
            item.id = SnowFlake().nextId()
        }
        item.persist()
        return item
    }

    override fun update(id: Long, item: Country): Country? {
        val entity: Country? = Country.findById(id)
        entity?.name = item.name
        return entity
    }

    override fun delete(id: Long) {
        val entity: Country? = Country.findById(id)
        entity?.delete()
    }
}