package com.orange.shopeenback.model

import io.micronaut.data.annotation.*
import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId
import java.time.Instant

@MappedEntity
@Schema(description = "Mesures de performance de l'application.")
data class Mesure(
    @field:Id
    @field:GeneratedValue
    var id: ObjectId? = null,

    @field:Schema(description = "Charge moyenne du processeur")
    var chargeProcMoy: Double,

    @field:Schema(description = "Durée en heures de la mesure")
    var duree: Double,

    @field:Schema(description = "Horodate de la mesure")
    var horodate: Instant = Instant.now(),

    @field:Relation(value = Relation.Kind.MANY_TO_ONE)
    var application: Application
)