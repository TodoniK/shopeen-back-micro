package com.orange.shopeenback.controller

import com.orange.shopeenback.dto.ApplicationDTO
import com.orange.shopeenback.dto.ConfigurationDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import com.orange.shopeenback.service.ApplicationService
import org.bson.types.ObjectId

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ApplicationController(private val applicationService: ApplicationService) {
    @Get("/applications")
    @Operation(summary = "List all applications", description = "Returns a list of all applications.")
    @ApiResponse(responseCode = "200", description = "Applications list returned")
    fun listAll(): HttpResponse<List<ApplicationDTO>> {
        val applications = applicationService.listAll()
        return HttpResponse.ok(applications)
    }

    @Post("/application")
    @Operation(summary = "Create an application", description = "Creates a new application.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Application created"),
        ApiResponse(responseCode = "400", description = "Invalid input"),
        ApiResponse(responseCode = "409", description = "Application already exists")
    ])
    fun createApplication(@Body applicationDTO: ApplicationDTO): HttpResponse<ApplicationDTO> {
        val createdApplication = applicationService.createApplication(applicationDTO)
        return HttpResponse.created(createdApplication)
    }

    @Put("/application/{id}")
    @Operation(summary = "Update an application", description = "Updates an existing application.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Application updated"),
        ApiResponse(responseCode = "400", description = "Invalid input"),
        ApiResponse(responseCode = "404", description = "Application not found"),
        ApiResponse(responseCode = "409", description = "Conflict, e.g., duplicate name")
    ])
    fun updateApplication(@PathVariable id: ObjectId, @Body applicationDTO: ApplicationDTO): HttpResponse<ApplicationDTO> {
        val updatedApplication = applicationService.updateApplication(id, applicationDTO)
        return HttpResponse.ok(updatedApplication)
    }

    @Delete("/application/{id}")
    @Operation(summary = "Delete an application", description = "Deletes an existing application.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Application deleted"),
        ApiResponse(responseCode = "404", description = "Application not found")
    ])
    fun deleteApplication(@PathVariable id: ObjectId): HttpResponse<Unit> {
        applicationService.deleteApplication(id)
        return HttpResponse.ok()
    }

    @Get("/application/{id}/configuration")
    @Operation(summary = "Get the configuration of an application", description = "Returns the configuration of an application.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Configuration returned"),
        ApiResponse(responseCode = "404", description = "Application not found")
    ])
    fun getConfigurationOfAppFromId(@PathVariable id: ObjectId): HttpResponse<ConfigurationDTO> {
        val configuration = applicationService.getConfigurationOfAppFromId(id)
        return HttpResponse.ok(configuration)
    }
}