package com.orange.shopeenback.service

import com.orange.shopeenback.dto.MesureDTO
import com.orange.shopeenback.model.Mesure
import com.orange.shopeenback.repository.MesureRepository
import jakarta.inject.Singleton
import org.bson.types.ObjectId
import java.time.Instant

@Singleton
class MesureService(private val mesureRepository: MesureRepository) {
    fun listAll(): List<MesureDTO> {
        return mesureRepository.findAll().map { it.toDTO() }
    }

    fun listMesuresByApplication(applicationId: ObjectId): List<MesureDTO> {
        return mesureRepository.findByApplicationId(applicationId).map { it.toDTO() }
    }

    fun createMesure(mesureDTO: MesureDTO): MesureDTO {
        val mesure = mesureDTO.toEntity()
        mesureRepository.save(mesure)
        return mesure.toDTO()
    }

    fun updateMesure(mesureId: ObjectId, mesureDTO: MesureDTO): MesureDTO {
        val mesureOptional = mesureRepository.findById(mesureId)
        if (!mesureOptional.isPresent) {
            throw RuntimeException("Mesure not found")
        }
        val mesure = mesureOptional.get()
        mesure.chargeProcMoy = mesureDTO.chargeProcMoy
        mesure.duree = mesureDTO.duree
        mesure.horodate = Instant.now()
        mesureRepository.update(mesure)
        return mesure.toDTO()
    }

    fun deleteMesure(mesureId: ObjectId) {
        mesureRepository.deleteById(mesureId)
    }
}

fun Mesure.toDTO() = MesureDTO(
    id = this.id,
    chargeProcMoy = this.chargeProcMoy,
    duree = this.duree,
    horodate = this.horodate.toString(),
    application = this.application.toDTO()
)

fun MesureDTO.toEntity(): Mesure {
    return Mesure(
        id = this.id ?: ObjectId(),
        chargeProcMoy = this.chargeProcMoy,
        duree = this.duree,
        horodate = Instant.now(),
        application = this.application.toEntity()
    )
}