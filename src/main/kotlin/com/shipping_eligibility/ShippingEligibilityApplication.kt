package com.shipping_eligibility

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class ShippingEligibilityApplication {

	@GetMapping("/")
	fun home(): String {
		return "Hello World!"
	}
}

fun main(args: Array<String>) {
	runApplication<ShippingEligibilityApplication>(*args)
}
