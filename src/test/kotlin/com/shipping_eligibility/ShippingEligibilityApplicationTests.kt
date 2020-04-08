package com.shipping_eligibility

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

//Borrowed test code setup from https://medium.com/@crsandeep/creating-and-testing-a-kotlin-restful-web-services-using-spring-boot-1a11aeda279e
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ShippingEligibilityApplicationTests {

	@Autowired
	lateinit var testRestTemplate: TestRestTemplate

	@Test
	fun contextLoads() {
	}

	@Test
	fun noParamsShouldSendErrorStatus() {
		val result = testRestTemplate.getForEntity(PRIMARY_ENDPOINT, String::class.java)
		Assertions.assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
	}

	@Test
	fun blankParamsShouldThrowException() {
		val badRequest = "?title=''&sender=''&category=''&price=''"
		val result = testRestTemplate.getForEntity(PRIMARY_ENDPOINT+badRequest, String::class.java)
		Assertions.assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
	}

	@Test
	fun goodRequestReturnsWithoutError() {
		val goodRequest = "?title=ExampleItem&sender=user12345&category=2&price=2.99"
		val result = testRestTemplate.getForEntity(PRIMARY_ENDPOINT+goodRequest, String::class.java)
		//println(result.body)
		Assertions.assertNotNull(result.body)
	}

	@Test
	fun checkSellerWithEnrolledSeller() {
		val list: Array<String> = arrayOf("user1", "randomuser", "user2")
		val req = EligibilityRequest("Example", "user1", 1, 30.01)
		Assertions.assertEquals(true, req.checkSeller(list))
	}

	@Test
	fun checkSellerWithUnEnrolledSeller() {
		val list: Array<String> = arrayOf("user1", "randomuser", "user2")
		val req = EligibilityRequest("Example", "notEnrolled", 1, 30.01)
		Assertions.assertEquals(false, req.checkSeller(list))
	}

	@Test
	fun checkCategoryWithApprovedCategory() {
		val list: Array<Int> = arrayOf(1, 3, 5 ,6)
		val req = EligibilityRequest("Example", "user1", 1, 30.01)
		Assertions.assertEquals(true, req.checkCategory(list))
	}

	@Test
	fun checkCategoryWithNotApprovedCategory() {
		val list: Array<Int> = arrayOf(1, 3, 5 ,6)
		val req = EligibilityRequest("Example", "user1", 2, 30.01)
		Assertions.assertEquals(false, req.checkCategory(list))
	}

	@Test
	fun checkPriceAboveMinPrice() {
		val minPrice: Double = 10.99
		val req = EligibilityRequest("Example", "user1", 2, 30.01)
		Assertions.assertEquals(true, req.checkPrice(minPrice))
	}

	@Test
	fun checkPriceBelowMinPrice() {
		val minPrice: Double = 10.99
		val req = EligibilityRequest("Example", "user1", 2, 2.01)
		Assertions.assertEquals(false, req.checkPrice(minPrice))
	}
}
