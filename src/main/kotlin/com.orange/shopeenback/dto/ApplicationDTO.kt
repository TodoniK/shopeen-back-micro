package com.orange.shopeenback.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId

@Schema(description = "DTO pour les données de l'application.")
data class ApplicationDTO(
    var id: ObjectId? = null,
    var nomApp: String,
    var configuration: ConfigurationDTO
)
