package tech.shopeenapi.entity

import io.swagger.v3.oas.annotations.media.Schema
import kotlin.math.roundToInt

@Schema(description = "Bilan of power, CO2 and money consumption, based on user responses")
class Bilan(
    private val userResponse: Int = 0,
    private val prixKhwEntreprise: Double = 0.1388,
    private val coefKgeqCO2: Double = 0.1
) {
    @field:Schema(
        description = "Private bilan value, represents money consumed by all equipment",
        example = "327.25",
        type = "double",
    )
    var bilanEuro = userResponse * prixKhwEntreprise

    @field:Schema(
        description = "Private bilan value, represents Kwh consumed by all equipment",
        example = "327.25",
        type = "double",
    )
    var bilanKwh = userResponse

    @field:Schema(
        description = "Private bilan value, represents CO2 consumed by all equipment",
        example = "327.25",
        type = "double",
    )
    var bilanKgeqCO2 = userResponse * coefKgeqCO2

    fun calculerBilan(tabResponses : List<Response>) : Bilan {

        for(response in tabResponses){
            this.bilanKwh = (this.bilanKwh + response.userResponse * response.consoMoy).roundToInt()
            this.bilanEuro = this.bilanEuro + response.userResponse * prixKhwEntreprise * response.consoMoy
            this.bilanKgeqCO2 = this.bilanKgeqCO2 + response.userResponse * coefKgeqCO2 * response.consoMoy
        }

        return this
    }
}
