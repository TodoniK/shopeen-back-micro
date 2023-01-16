package tech.shopeenapi.entity

import kotlin.math.roundToInt

class Bilan(
    private val userResponse: Int = 0,
    private val prixKhwEntreprise: Double = 0.1388,
    private val coefKgeqCO2: Double = 0.1
) {
    var bilanEuro = userResponse * prixKhwEntreprise
    var bilanKwh = userResponse
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
