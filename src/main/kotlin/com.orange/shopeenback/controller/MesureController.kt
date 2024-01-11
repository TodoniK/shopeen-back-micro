package com.orange.shopeenback.controller

import com.orange.shopeenback.dto.MesureDTO
import com.orange.shopeenback.service.MesureService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put
import io.micronaut.http.annotation.Delete
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.bson.types.ObjectId

@Controller("/api")
@ExecuteOn(TaskExecutors.IO)
class MesureController(private val mesureService: MesureService) {
    @Get("/mesures/by-application/{applicationId}")
    @Operation(summary = "List measures by application", description = "Returns a list of measures for a specific application.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Measures list returned"),
        ApiResponse(responseCode = "404", description = "Application not found")
    ])
    fun listMesuresByApplication(@PathVariable applicationId: ObjectId): HttpResponse<List<MesureDTO>> {
        val mesures = mesureService.listMesuresByApplication(applicationId)
        return HttpResponse.ok(mesures)
    }

    @Get("/mesures")
    @Operation(summary = "List all measures", description = "Returns a list of all measures.")
    fun listAll(): HttpResponse<List<MesureDTO>> {
        val mesures = mesureService.listAll()
        return HttpResponse.ok(mesures)
    }

    @Post("/mesure")
    @Operation(summary = "Create a measure", description = "Creates a new measure.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Measure created"),
        ApiResponse(responseCode = "400", description = "Invalid input"),
        ApiResponse(responseCode = "409", description = "Measure already exists")
    ])
    fun createMesure(mesureDTO: MesureDTO): HttpResponse<MesureDTO> {
        val createdMesure = mesureService.createMesure(mesureDTO)
        return HttpResponse.created(createdMesure)
    }

    @Put("/mesure/{id}")
    @Operation(summary = "Update a measure", description = "Updates an existing measure.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Measure updated"),
        ApiResponse(responseCode = "400", description = "Invalid input"),
        ApiResponse(responseCode = "404", description = "Measure not found"),
        ApiResponse(responseCode = "409", description = "Conflict, e.g., duplicate name")
    ])
    fun updateMesure(@PathVariable id: ObjectId, mesureDTO: MesureDTO): HttpResponse<MesureDTO> {
        val updatedMesure = mesureService.updateMesure(id, mesureDTO)
        return HttpResponse.ok(updatedMesure)
    }

    @Delete("/mesure/{id}")
    @Operation(summary = "Delete a measure", description = "Deletes an existing measure.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Measure deleted"),
        ApiResponse(responseCode = "404", description = "Measure not found")
    ])
    fun deleteMesure(@PathVariable id: ObjectId): HttpResponse<Unit> {
        mesureService.deleteMesure(id)
        return HttpResponse.ok()
    }
}