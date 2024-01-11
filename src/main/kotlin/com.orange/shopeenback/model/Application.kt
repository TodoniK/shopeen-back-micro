package com.orange.shopeenback.model

import io.micronaut.data.annotation.*
import io.swagger.v3.oas.annotations.media.Schema
import org.bson.types.ObjectId

@MappedEntity
@Schema(description = "Représente une application avec sa configuration associée.")
data class Application(
    @field:Id
    @field:GeneratedValue
    var id: ObjectId? = null,

    @field:Schema(description = "Nom de l'application")
    var nomApp: String,

    @field:Relation(value = Relation.Kind.ONE_TO_ONE, mappedBy = "application")
    var configuration: Configuration
)