package com.orange.shopeenback.unit

import com.orange.shopeenback.model.Application
import com.orange.shopeenback.model.Configuration
import com.orange.shopeenback.repository.ApplicationRepository
import com.orange.shopeenback.repository.ConfigurationRepository
import com.orange.shopeenback.service.ApplicationService
import com.orange.shopeenback.service.toDTO
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.bson.types.ObjectId

class ApplicationServiceTest {

    private val applicationRepository = mockk<ApplicationRepository>(relaxed = true)
    private val configurationRepository = mockk<ConfigurationRepository>(relaxed = true)
    private val applicationService = ApplicationService(applicationRepository, configurationRepository)

    @Test
    fun `should list all applications`() {
        // Arrange
        val applications = listOf(Application(ObjectId(), "TestApp", Configuration(ObjectId(), 1, 1, 1, 1, 1)))
        every { applicationRepository.findAll() } returns applications

        // Act
        val result = applicationService.listAll()

        // Assert
        verify(exactly = 1) { applicationRepository.findAll() }
        assertEquals(applications.map(Application::toDTO), result)
    }
}