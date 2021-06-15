package com.snowgrey.car.pojo

import com.snowgrey.base.BasePojo

class CarModelPojo : BasePojo() {

    var brand: CarBrandPojo? = null
    var brandName: String = ""

    var factory: CarFactoryPojo? = null
    var factoryName: String = ""

    //丰田-雷凌
    var name: String = ""
    var seriesState: String = ""
    var seriesOrder: Int = 0

}