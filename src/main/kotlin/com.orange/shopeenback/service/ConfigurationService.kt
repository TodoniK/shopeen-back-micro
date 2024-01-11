package com.orange.shopeenback.service

import com.orange.shopeenback.dto.ConfigurationDTO
import com.orange.shopeenback.model.Configuration
import com.orange.shopeenback.repository.ConfigurationRepository
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import java.util.*

@Singleton
class ConfigurationService(private val configurationRepository: ConfigurationRepository) {
    fun getConfiguration(configurationId: ObjectId): ConfigurationDTO {
        val configurationOptional: Optional<Configuration> = configurationRepository.findById(configurationId)
        if (!configurationOptional.isPresent) {
            throw RuntimeException("Configuration not found")
        }
        return configurationOptional.get().toDTO()
    }
}

fun Configuration.toDTO() = ConfigurationDTO(
    id = this.id,
    nbConteneurs = this.nbConteneurs,
    nbProcesseurs = this.nbProcesseurs,
    nbPC = this.nbPC,
    nbMoniteurs = this.nbMoniteurs,
    nbDev = this.nbDev
)