package com.snowgrey.car.pojo

import com.snowgrey.base.BasePojo

class CarModelTypePojo : BasePojo() {

    var brand: CarBrandPojo? = null
    var brandName: String = ""

    var factory: CarFactoryPojo? = null
    var factoryName: String = ""

    var model: CarModelPojo? = null
    var modelName: String = ""

    var modelYear: CarModelYearPojo? = null

    //丰田-雷凌 / 2021款 / 2021款 TNGA 1.5L CVT进取版
    var name: String = ""
    var state: String = ""

    var minPrice: Double = 0.0
    var maxPrice: Double = 0.0

}