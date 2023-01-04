package tech.shopeenapi.service

import tech.shopeenapi.dto.ResponseDTO
import tech.shopeenapi.entity.Response
import tech.shopeenapi.repository.ResponseRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton

@Singleton
class ResponseService(
    private val responseRepository: ResponseRepository
) {

    fun create(response: ResponseDTO): Response {
        val optionalResponse = responseRepository.existsById(response.idQuestion)
        return if(!optionalResponse){
            responseRepository.save(
                response.toResponseEntity()
            )
        } else{
            responseRepository.update(response.toResponseEntity())
        }
    }

    fun findAll(): List<Response> =
        responseRepository
            .findAll()
            .toList()

    fun findById(id: String): Response =
        responseRepository.findById(id)
            .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Response with id: $id was not found.") }


    fun deleteById(id: String) {
        val foundUser = findById(id)

        responseRepository.delete(foundUser)
    }

    private fun ResponseDTO.toResponseEntity(): Response {

        return Response(
            idQuestion = this.idQuestion,
            userResponse = this.userResponse,

        )
    }
}