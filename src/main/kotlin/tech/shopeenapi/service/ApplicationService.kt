package tech.shopeenapi.service

import jakarta.inject.Singleton
import org.bson.types.ObjectId
import tech.shopeenapi.dto.ApplicationDTO
import tech.shopeenapi.entity.Application
import tech.shopeenapi.repository.ApplicationRepository
import java.time.LocalDate

@Singleton
class ApplicationService(
    private val applicationRepository: ApplicationRepository
) {
    fun getApplicationsHistorical(): List<Application> {
        return applicationRepository.findAll().map { it.toAppEntity() }
    }

    fun createApplicationHistorical(application: Application): Application? {
        return if (application.idExtName != "Default app name") {
            applicationRepository.save(application.toAppDTO()).toAppEntity()
        } else {
            null
        }
    }

    fun getApplicationsHistoricalByName(name: String): List<Application> {
        return applicationRepository.findApplicationDTOByIdExtName(name).map { it.toAppEntity() }
    }

}
fun Application.toAppDTO() = ApplicationDTO(
    idInterne = ObjectId(),
    idExtName = this.idExtName,
    bilanEuro = this.bilanEuro,
    bilanCO2 = this.bilanCO2,
    bilanEnergy = this.bilanEnergy,
    measurementDate = LocalDate.now().toString()
)

fun ApplicationDTO.toAppEntity() = Application(
    idExtName = this.idExtName,
    bilanEuro = this.bilanEuro,
    bilanCO2 = this.bilanCO2,
    bilanEnergy = this.bilanEnergy,
    measurementDate = this.measurementDate
)