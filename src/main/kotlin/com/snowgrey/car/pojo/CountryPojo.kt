package com.snowgrey.car.pojo

import com.snowgrey.base.BaseEntity
import com.snowgrey.base.BasePojo
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import javax.persistence.*

class CountryPojo : BasePojo() {

    var name: String = ""

}

