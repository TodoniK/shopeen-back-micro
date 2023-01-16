package tech.shopeenapi.entity

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import org.bson.types.ObjectId

@MappedEntity
data class Application(
    @field:Id
    var id: ObjectId? = null,
    val idExtName: String,
    val bilanEuro: Int,
    val bilanCO2: Int,
    val bilanEnergy: Int,
    var measurementDate: String
)