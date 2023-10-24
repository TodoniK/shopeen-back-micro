package com.orange.shopeenback.entity

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class Application(

    @field:Schema(
        description = "Question identifier, used to manage responses",
        example = "nbPC",
        type = "string",
    )
    val idExtName: String,

    @field:Schema(
        description = "Financial cost of the measured system, based on its energy consumption",
        example = "16.7",
        type = "int",
        minimum = "0",
        maximum = "inf"
    )
    val bilanEuro: Int,

    @field:Schema(
        description = "CO2 production of the measured system",
        example = "16.7",
        type = "int",
        minimum = "0",
        maximum = "inf"
    )
    val bilanCO2: Int,

    @field:Schema(
        description = "Energy consumption of the measured system",
        example = "16.7",
        type = "int",
        minimum = "0",
        maximum = "inf"
    )
    val bilanEnergy: Int,

    @field:Schema(
        description = "Measurement date of the system bilan",
        example = "16/02/2002 16:45",
    )
    var measurementDate: String = LocalDate.now().toString()
)