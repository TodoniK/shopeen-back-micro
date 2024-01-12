package com.orange.shopeenback.integration

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Inject
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

@MicronautTest
class ApplicationControllerTest {

    @Inject
    @field:Client("/api")
    lateinit var client: HttpClient

//    @Test
//    fun `should list all applications via endpoint`() {
//        // Arrange
//        val request = HttpRequest.GET<String>("/applications")
//
//        // Act
//        val response = client.toBlocking().exchange(request, String::class.java)
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.status)
//        // Vous devriez vérifier le contenu de la réponse ici
//    }
}