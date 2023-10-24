package com.orange.shopeenback.dto

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity(value="response")
data class ResponseDTO(
    @field:Id
    val idQuestion: String,
    val userResponse: Int = 0,
    val consoMoy: Double = 0.0
)
