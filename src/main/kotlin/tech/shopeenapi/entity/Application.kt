package tech.shopeenapi.entity

import java.time.LocalDate

data class Application(
    val idExtName: String,
    val bilanEuro: Int,
    val bilanCO2: Int,
    val bilanEnergy: Int,
    var measurementDate: String = LocalDate.now().toString()
)