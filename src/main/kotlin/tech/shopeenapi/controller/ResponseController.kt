package tech.shopeenapi.controller

import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.NO_CONTENT
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import tech.shopeenapi.entity.Bilan
import tech.shopeenapi.entity.Response
import tech.shopeenapi.service.ResponseService

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ResponseController(
    private val responseService: ResponseService
) {

    @Get("/responses")
    fun getAllResponses(): List<Response> =
        responseService.getResponses()

    @Get("/responses/{id}")
    fun getResponseById(@PathVariable id: String): Response? =
        responseService.getResponseById(id)

    @Post("/responses")
    @Status(CREATED)
    fun createResponse(@Body request: Response): Response =
        responseService.createResponse(request)

    @Delete("responses/{id}")
    @Status(NO_CONTENT)
    fun deleteResponseById(@PathVariable id: String) =
        responseService.deleteResponse(id)

    @Get("/bilan")
    fun getBilan() : Bilan? =
        responseService.getBilan()
}