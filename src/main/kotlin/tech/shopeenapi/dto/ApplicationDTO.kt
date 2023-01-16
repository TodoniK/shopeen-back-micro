package tech.shopeenapi.dto

import org.bson.types.ObjectId
import java.time.LocalDate

data class ApplicationDTO(
    var _id: ObjectId = ObjectId(),
    val idExtName: String = "Default application name",
    val bilanEuro: Int = 0,
    val bilanCO2: Int = 0,
    val bilanEnergy: Int = 0,
    val measurementDate: LocalDate = LocalDate.now()
)
