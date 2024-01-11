package com.orange.shopeenback.controller

import com.orange.shopeenback.dto.ConfigurationDTO
import com.orange.shopeenback.service.ConfigurationService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.bson.types.ObjectId

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class ConfigurationController(private val configurationService: ConfigurationService) {
    @Get("/configuration/{id}")
    @Operation(summary = "Get a configuration", description = "Returns a configuration by its ID.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Configuration found"),
        ApiResponse(responseCode = "404", description = "Configuration not found")
    ])
    fun getConfiguration(@PathVariable id: ObjectId): HttpResponse<ConfigurationDTO> {
        val configuration = configurationService.getConfiguration(id)
        return HttpResponse.ok(configuration)
    }

    @Get("/configuration/update/{id}")
    @Operation(summary = "Update a configuration", description = "Update a configuration by its ID.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Configuration updated"),
        ApiResponse(responseCode = "404", description = "Configuration not found")
    ])
    fun updateConfiguration(@PathVariable id: ObjectId, configurationDTO: ConfigurationDTO): HttpResponse<ConfigurationDTO> {
        val configuration = configurationService.updateConfiguration(id, configurationDTO)
        return HttpResponse.ok(configuration)
    }
}