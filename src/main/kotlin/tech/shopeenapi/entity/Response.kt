package tech.shopeenapi.entity

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
data class Response(
    @field:Id
    val idQuestion: String? = null,
    val userResponse: Int,
)