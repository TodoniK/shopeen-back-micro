package com.orange.shopeenback.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId

@Schema(description = "DTO pour une mesure énergétique de l'application.")
data class MesureDTO(
    var id : ObjectId? = null,
    var chargeProcMoy: Double,
    var duree: Double,
    var horodate: String,
    var application: ApplicationDTO? = null
)