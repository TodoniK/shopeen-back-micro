package com.orange.shopeenback.dto

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import org.bson.types.ObjectId
import java.time.LocalDate

@MappedEntity(value="application")
data class ApplicationDTO(
    @field:Id
    var idInterne: ObjectId = ObjectId(),
    val idExtName: String,
    val bilanEuro: Int,
    val bilanCO2: Int,
    val bilanEnergy: Int,
    val measurementDate: String = LocalDate.now().toString()
)
