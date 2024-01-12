package com.orange.shopeenback.model

import io.micronaut.data.annotation.*
import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId

@MappedEntity
@Schema(description = "Configuration de l'application.")
data class Configuration(
    @field:Id
    @field:GeneratedValue
    var id: ObjectId? = null,

    @field:Schema(description = "Nombre de conteneurs")
    var nbConteneurs: Int,

    @field:Schema(description = "Unités de temps CPU maximum que le conteneur peut utiliser")
    var quotaCPU: Int,

    @field:Schema(description = "Nombre de PC développeurs")
    var nbOrdisDev: Int,

    @field:Schema(description = "Nombre de moniteurs")
    var nbMoniteurs: Int,

    @field:Schema(description = "Nombre de développeurs travaillant sur l'appli")
    var nbDev: Int
)