package com.orange.shopeenback.entity

import io.swagger.v3.oas.annotations.media.Schema

data class Response(

    @field:Schema(
        description = "Question identifier, used to manage responses",
        example = "nbPC",
        type = "string",
    )
    val idQuestion: String,

    @field:Schema(
        description = "Value sent by the user, corresponding to a number of things",
        example = "16",
        type = "int",
        minimum = "0",
        maximum = "999"
    )
    val userResponse: Int,

    @field:Schema(
        description = "Average consumption of the entity measured",
        example = "16",
        type = "double",
    )
    val consoMoy: Double
)