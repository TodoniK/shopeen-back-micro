package com.orange.shopeenback.service

import com.orange.shopeenback.dto.ApplicationDTO
import com.orange.shopeenback.dto.ConfigurationDTO
import com.orange.shopeenback.model.Application
import com.orange.shopeenback.model.Configuration
import com.orange.shopeenback.repository.ApplicationRepository
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import java.util.*

@Singleton
class ApplicationService(private val applicationRepository: ApplicationRepository) {

    fun listAll(): List<ApplicationDTO> {
        return applicationRepository.findAll().map(Application::toDTO)
    }

    fun createApplication(applicationDTO: ApplicationDTO): ApplicationDTO {
        val application = applicationDTO.toEntity()
        applicationRepository.save(application)
        return application.toDTO()
    }

    fun updateApplication(applicationId: ObjectId, applicationDTO: ApplicationDTO): ApplicationDTO {
        val applicationOptional: Optional<Application> = applicationRepository.findById(applicationId)
        if (!applicationOptional.isPresent) {
            throw RuntimeException("Application not found")
        }
        val application = applicationOptional.get()
        application.nomApp = applicationDTO.nomApp
        applicationRepository.update(application)
        return application.toDTO()
    }
}

fun Application.toDTO(): ApplicationDTO {
    return ApplicationDTO(
        id = this.id,
        nomApp = this.nomApp,
        configuration = this.configuration.toDTO()
    )
}

fun ApplicationDTO.toEntity(): Application {
    return Application(
        id = this.id ?: ObjectId(),
        nomApp = this.nomApp,
        configuration = this.configuration.toEntity()
    )
}

fun ConfigurationDTO.toEntity(): Configuration {
    return Configuration(
        id = this.id ?: ObjectId(),
        nbConteneurs = this.nbConteneurs,
        nbProcesseurs = this.nbProcesseurs,
        nbPC = this.nbPC,
        nbMoniteurs = this.nbMoniteurs,
        nbDev = this.nbDev
    )
}