package tech.shopeenapi.service

import tech.shopeenapi.dto.ResponseDTO
import tech.shopeenapi.entity.Response
import tech.shopeenapi.repository.ResponseRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton
import tech.shopeenapi.entity.Bilan

@Singleton
class ResponseService(
    private val responseRepository: ResponseRepository
) {

    fun createResponse(response: ResponseDTO): Response {
        val optionalResponse = responseRepository.existsById(response.idQuestion)
        return if(!optionalResponse){
            responseRepository.save(
                response.toResponseEntity()
            )
        } else{
            responseRepository.update(response.toResponseEntity())
        }
    }

    fun getResponses(): List<Response> =
        responseRepository
            .findAll()
            .toList()

    fun getResponseById(idQuestion: String): Response =
        responseRepository.findById(idQuestion)
            .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Response with id: $idQuestion was not found.") }


    fun deleteResponse(idQuestion: String) {
        val foundUser = getResponseById(idQuestion)

        responseRepository.delete(foundUser)
    }

    fun getBilan(): Bilan? {
        return if(this.getResponses().isNotEmpty()){
            Bilan().calculerBilan(this.getResponses())
        }else {
            null
        }
    }

    private fun ResponseDTO.toResponseEntity(): Response {

        return Response(
            idQuestion = this.idQuestion,
            userResponse = this.userResponse,
            consoMoy = this.consoMoy
        )
    }
}