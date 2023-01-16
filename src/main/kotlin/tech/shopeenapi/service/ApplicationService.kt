package tech.shopeenapi.service

import jakarta.inject.Singleton
import tech.shopeenapi.dto.ApplicationDTO
import tech.shopeenapi.entity.Application
import tech.shopeenapi.repository.ApplicationRepository

@Singleton
class ApplicationService(
    private val applicationRepository: ApplicationRepository
) {

    fun createApplicationHistorical(application: ApplicationDTO): Application =
        applicationRepository.save(application.toAppEntity())

    fun getApplicationsHistorical(): List<Application> =
        applicationRepository.findAll().toList()

    fun getApplicationsHistoricalByName(name: String): List<Application> =
        applicationRepository.findApplicationByIdExtName(name)


    private fun ApplicationDTO.toAppEntity() = Application(
        idExtName = this.idExtName,
        bilanEuro = this.bilanEuro,
        bilanCO2 = this.bilanCO2,
        bilanEnergy = this.bilanEnergy,
        measurementDate = this.measurementDate.toString()
    )
}