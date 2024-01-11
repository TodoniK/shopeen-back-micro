package com.orange.shopeenback.service

import com.orange.shopeenback.dto.MesureDTO
import com.orange.shopeenback.model.Mesure
import com.orange.shopeenback.repository.MesureRepository
import jakarta.inject.Singleton
import org.bson.types.ObjectId

@Singleton
class MesureService(private val mesureRepository: MesureRepository) {
    fun listMesuresByApplication(applicationId: ObjectId): List<MesureDTO> {
        return mesureRepository.findByApplicationId(applicationId).map { it.toDTO() }
    }
}

fun Mesure.toDTO() = MesureDTO(
    id = this.id,
    chargeProcMoy = this.chargeProcMoy,
    duree = this.duree,
    horodate = this.horodate.toString(),
    application = this.application.toDTO()
)