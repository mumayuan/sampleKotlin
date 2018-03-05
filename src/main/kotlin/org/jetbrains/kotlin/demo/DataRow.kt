package org.jetbrains.kotlin.demo

import kotlinx.serialization.*
import kotlinx.serialization.json.JSON
@Serializable
data  class DataRow(
        val pvalue: Double,
        val bucket: String,
        val xname: String
)

