package tech.shopeenapi.controller

import io.micronaut.http.HttpStatus.CREATED
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import tech.shopeenapi.dto.ApplicationDTO
import tech.shopeenapi.entity.Application
import tech.shopeenapi.service.ApplicationService

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ApplicationController(
    private val applicationService: ApplicationService
) {

    @Get("/appli")
    fun getApplicationsHistorical(): List<Application> =
        applicationService.getApplicationsHistorical()

    @Get("/appli/{id}")
    fun getApplicationHistoricalByName(@PathVariable id: String): List<Application> =
        applicationService.getApplicationsHistoricalByName(id)

    @Post("/appli")
    @Status(CREATED)
    fun postAppBilan(@Body app: ApplicationDTO): Application =
        applicationService.createApplicationHistorical(app)
}