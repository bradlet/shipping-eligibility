package com.shipping_eligibility

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

const val PRIMARY_ENDPOINT = "/checkAccess"
const val DATAPATH = "src/main/resources/data/"

@SpringBootApplication
@RestController
class ShippingEligibilityApplication {

	// Not secure but I don't have to spin up a database to get persistence this way.
	// A quick temporary solution.
	val listOfSellers: Array<String> = fileManager()
			.readFile(DATAPATH+"enrolled.txt")
	val listOfCategories: Array<Int> = fileManager()
			.readFile(DATAPATH+"approvedCategories.txt")
			.map {it.toInt()}.toTypedArray()
	val minPrice: Double = fileManager()
			.readFile(DATAPATH+"minPrice.txt")
			.map {it.toDouble()}[0] // Treated as an array to reuse the readFile method

	//Note: at the moment, default endpoint ("/") is served by resources/static/index.html
	//		>> A basic html form to send requests to /checkAccess

	//Endpoint for shipping eligibility API calls
	@GetMapping(path=[PRIMARY_ENDPOINT], produces=["application/json"])
	fun checkAccess(@RequestParam(defaultValue="none", required=true) title: String,
					@RequestParam(defaultValue="none", required=true) seller: String,
					@RequestParam(defaultValue="none", required=true) category: Int,
					@RequestParam(defaultValue="none", required=true) price: Double
	): ResponseEntity<Boolean> {
		println("$PRIMARY_ENDPOINT request received: $title, $seller, $category, $price")
		val req = EligibilityRequest(title, seller, category, price)
		req.validate()

		return ResponseEntity(req.checkRequest(listOfSellers, listOfCategories, minPrice),
			HttpStatus.OK)
	}
}

fun main(args: Array<String>) {
	runApplication<ShippingEligibilityApplication>(*args)
}

