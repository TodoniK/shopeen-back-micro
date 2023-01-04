package tech.shopeenapi.entity

class Bilan(
    private val userResponse: Int = 0,
    private val coefEuro: Double = 1.17,
    private val coefKwh: Double = 2.36,
    private val coefKgeqCO2: Double = 0.69
) {
    var bilanEuro = userResponse * coefEuro
    var bilanKwh = userResponse * coefKwh
    var bilanKgeqCO2 = userResponse * coefKgeqCO2

    fun calculerBilan(tabResponses : List<Response>) : Bilan {

        for(response in tabResponses){
            this.bilanKwh = this.bilanKwh + response.userResponse * coefKwh
            this.bilanEuro = this.bilanEuro + response.userResponse * coefEuro
            this.bilanKgeqCO2 = this.bilanKgeqCO2 + response.userResponse * coefKgeqCO2
        }

        return this
    }
}
