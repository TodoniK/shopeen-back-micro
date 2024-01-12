package com.orange.shopeenback.service

import com.orange.shopeenback.dto.ApplicationDTO
import com.orange.shopeenback.dto.ConfigurationDTO
import com.orange.shopeenback.model.Application
import com.orange.shopeenback.model.Configuration
import com.orange.shopeenback.repository.ApplicationRepository
import com.orange.shopeenback.repository.ConfigurationRepository
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import java.util.*

@Singleton
class ApplicationService(private val applicationRepository: ApplicationRepository, private val configurationRepository: ConfigurationRepository) {
    fun listAll(): List<ApplicationDTO> {
        return applicationRepository.findAll().map(Application::toDTO)
    }

    fun createApplication(applicationDTO: ApplicationDTO): ApplicationDTO {
        val configuration = applicationDTO.configuration.toEntity()
        configurationRepository.save(configuration)
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

    fun deleteApplication(applicationId: ObjectId) {
        applicationRepository.deleteById(applicationId)
    }

    fun getConfigurationOfAppFromId(applicationId: ObjectId) : ConfigurationDTO {
        val applicationOptional: Optional<Application> = applicationRepository.findById(applicationId)
        if(!applicationOptional.isPresent) {
            throw RuntimeException("Application not found")
        } else {
            val configId = applicationOptional.get().configuration.id
            val configurationOptional: Optional<Configuration> = configurationRepository.findById(configId)
            if(!configurationOptional.isPresent) {
                throw RuntimeException("Configuration not found")
            }
            return configurationOptional.get().toDTO()
        }
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
        quotaCPU = this.quotaCPU,
        nbOrdisDev = this.nbOrdisDev,
        nbMoniteurs = this.nbMoniteurs,
        nbDev = this.nbDev
    )
}