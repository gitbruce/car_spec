package com.snowgrey.car.pojo

import com.snowgrey.base.BasePojo

class CarModelYearPojo : BasePojo() {
    lateinit var brand: CarBrandPojo
    lateinit var brandName: String

    var factory: CarFactoryPojo? = null
    var factoryName: String = ""

    var model: CarModelPojo? = null
    var modelName: String = ""

    //丰田-雷凌-2021款
    var name: String = ""

}