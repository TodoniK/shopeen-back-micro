package tech.shopeenapi.controller

import tech.shopeenapi.dto.ResponseDTO
import tech.shopeenapi.entity.Response
import tech.shopeenapi.service.ResponseService
import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.NO_CONTENT
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ResponseController(
    private val responseService: ResponseService
) {

    @Get("/responses")
    fun getAllResponses(): List<Response> =
        responseService.findAll()

    @Get("/responses/{id}")
    fun getResponseById(@PathVariable id: String): Response =
        responseService.findById(id)

    @Post("/responses")
    @Status(CREATED)
    fun createResponse(@Body request: ResponseDTO): Response =
        responseService.create(request)

    @Delete("responses/{id}")
    @Status(NO_CONTENT)
    fun deleteResponseById(@PathVariable id: String) =
        responseService.deleteById(id)
}