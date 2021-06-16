package com.snowgrey.car.service

import com.snowgrey.car.entity.CarBrand
import com.snowgrey.util.SnowFlake
import io.quarkus.panache.common.Sort
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CarBrandService: BaseService<CarBrand> {
    override fun getAll(): List<CarBrand> {
        return CarBrand.listAll(Sort.by("name"))
    }

    override fun getSingle(id: Long): CarBrand? {
        return CarBrand.findById(id)
    }

    override fun create(item: CarBrand): CarBrand {
        if (item.id == null) {
            item.id = SnowFlake().nextId()
        }
        item.persist()
        return item
    }

    override fun update(id: Long, item: CarBrand): CarBrand? {
        val entity: CarBrand? = CarBrand.findById(id)
        entity?.name = item.name
        entity?.country?.id = item.country?.id
        entity?.countryName = item.countryName
        entity?.logo = item.logo
        return entity
    }

    override fun delete(id: Long) {
        val entity: CarBrand? = CarBrand.findById(id)
        entity?.delete()
    }
}