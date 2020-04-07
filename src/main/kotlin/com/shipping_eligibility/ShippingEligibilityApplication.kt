package com.shipping_eligibility

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class ShippingEligibilityApplication {

	@GetMapping("/")
	fun home(): String {
		return "Hello World!"
	}

	@GetMapping("/shipping")
	fun shipping(@RequestParam(value="title", defaultValue="none") name: String): Boolean {
		if (name == "none") {
			println("GET: No name supplied")
			return false
		}
		println("Title supplied: $name")
		return true
	}
}

fun main(args: Array<String>) {
	runApplication<ShippingEligibilityApplication>(*args)
}
