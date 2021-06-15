package com.snowgrey.base

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

 open class BasePojo : Serializable {
    open var id: Long? = null
}