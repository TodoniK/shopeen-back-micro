package tech.shopeenapi.controller

import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import tech.shopeenapi.dto.ApplicationDTO
import tech.shopeenapi.entity.Application
import tech.shopeenapi.service.ApplicationService

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ApplicationController(
    private val applicationService: ApplicationService
) {
    @Operation(summary = "Get all bilan of all applications", description = "Returns multiples dated bilans of Orange developed applications if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Apps historical tab is empty"),
        ]
    )
    @Get("/appli")
    fun getApplicationsHistorical(): List<Application> =
        applicationService.getApplicationsHistorical()

    @Operation(summary = "Get all bilan of one app", description = "Returns multiples dated bilans of Orange developed application if successful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "App historical tab is empty"),
        ]
    )
    @Get("/appli/{nomApp}")
    fun getApplicationHistoricalByName(@PathVariable nomApp: String): List<Application> =
        applicationService.getApplicationsHistoricalByName(nomApp)
    
    @Operation(summary = "Post a dated historic bilan of an Orange developed application", description = "Returns 200 if added correctly")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Bad inputs"),
        ]
    )
    @Post("/appli")
    @Status(CREATED)
    fun postAppBilan(@Body app: Application): Application? =
        applicationService.createApplicationHistorical(app)
}