package com.orange.shopeenback.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId

@Schema(description = "DTO pour la configuration de l'application.")
data class ConfigurationDTO(
    var id : ObjectId? = null,
    var nbConteneurs: Int,
    var quotaCPU: Int,
    var nbOrdisDev: Int,
    var nbMoniteurs: Int,
    var nbDev: Int
)