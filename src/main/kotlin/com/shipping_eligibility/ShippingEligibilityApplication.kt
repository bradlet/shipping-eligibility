package com.shipping_eligibility

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*

@SpringBootApplication
@RestController
class ShippingEligibilityApplication {

	//Note: at the moment, default endpoint ("/") is served by resources/static/index.html
	//		>> A basic html form to send requests to /checkAccess

	//Endpoint for shipping eligibility API calls
	@GetMapping(path=["/checkAccess"], produces=["application/json"])
	fun checkAccess(
		@RequestParam(defaultValue="none", required=true) title: String,
		@RequestParam(defaultValue="none", required=true) seller: String,
		@RequestParam(defaultValue="none", required=true) category: String,
		@RequestParam(defaultValue="none", required=true) price: Double
		): Boolean {
		println("/checkAccess request received: $title, $seller, $category, $price")
		val req = EligibilityRequest(title, seller, category, price)
		req.validate()
		// if good req
		return true
	}
}

fun main(args: Array<String>) {
	runApplication<ShippingEligibilityApplication>(*args)
}

