package com.shipping_eligibility

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.*

const val PRIMARY_ENDPOINT = "/checkAccess"

@SpringBootApplication
@RestController
class ShippingEligibilityApplication {

	//Note: at the moment, default endpoint ("/") is served by resources/static/index.html
	//		>> A basic html form to send requests to /checkAccess

	//Endpoint for shipping eligibility API calls
	@GetMapping(path=[PRIMARY_ENDPOINT], produces=["application/json"])
	fun checkAccess(@RequestParam(defaultValue="none", required=true) title: String,
					@RequestParam(defaultValue="none", required=true) seller: String,
					@RequestParam(defaultValue="none", required=true) category: Int,
					@RequestParam(defaultValue="none", required=true) price: Double
	): Boolean {
		println("$PRIMARY_ENDPOINT request received: $title, $seller, $category, $price")
		val req = EligibilityRequest(title, seller, category, price)
		req.validate()

		//return req.checkRequest(listOfSellers, listOfCategories, minPrice)

		// if good req
		return true
	}
}

fun main(args: Array<String>) {
	runApplication<ShippingEligibilityApplication>(*args)
}

