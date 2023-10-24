package com.orange.shopeenback.controller

import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.HttpStatus.NO_CONTENT
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import com.orange.shopeenback.entity.Bilan
import com.orange.shopeenback.entity.Response
import com.orange.shopeenback.service.ResponseService

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ResponseController(
    private val responseService: ResponseService
) {
    @Operation(summary = "Get all the responses sent by the user", description = "Returns all entities if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Responses tab is empty"),
        ]
    )
    @Get("/responses")
    fun getAllResponses(): List<Response> =
        responseService.getResponses()

    @Operation(summary = "Get a specific response by id", description = "Returns a unique response")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Response requested doesn't exists"),
        ]
    )
    @Get("/responses/{id}")
    fun getResponseById(@PathVariable id: String): Response? =
        responseService.getResponseById(id)

    @Operation(summary = "Send to DB a response entered by the user", description = "Returns 200 if added correctly")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Infos entered doesn't match with DB schema"),
        ]
    )
    @Post("/responses")
    @Status(CREATED)
    fun createResponse(@Body request: Response): Response =
        responseService.createResponse(request)

    @Operation(summary = "Delete a response by id", description = "Returns 200 if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Response doesn't exists"),
        ]
    )
    @Delete("responses/{id}")
    @Status(NO_CONTENT)
    fun deleteResponseById(@PathVariable id: String) =
        responseService.deleteResponse(id)

    @Operation(summary = "Get final power bilan with all responses", description = "Returns a bilan composed of 3 values if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Responses tab is empty"),
        ]
    )
    @Get("/bilan")
    fun getBilan() : Bilan? =
        responseService.getBilan()
}