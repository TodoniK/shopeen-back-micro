package com.orange.shopeenback.controller

import com.orange.shopeenback.dto.MesureDTO
import com.orange.shopeenback.service.MesureService
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
}