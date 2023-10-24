package com.orange.shopeenback

import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info


@OpenAPIDefinition(
	info = Info(
		title = "Shopeen API",
		version = "1.0",
		description = "API Backend de l'application d'estimation de consommation éléctrique (Shopeen)",
	)
)

object Application {
	@JvmStatic
	fun main(args: Array<String>) {
		run(Application::class.java)
	}
}