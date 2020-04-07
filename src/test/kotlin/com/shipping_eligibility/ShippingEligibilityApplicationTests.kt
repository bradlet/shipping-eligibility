package com.shipping_eligibility

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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
		val result = testRestTemplate.getForEntity("/checkAccess", String::class.java)
		Assertions.assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
	}

	@Test
	fun blankParamsShouldThrowException() {
		val badRequest = "?title=''&sender=''&category=''&price='-1'"
		val result = testRestTemplate.getForEntity("/checkAccess$badRequest", String::class.java)
		Assertions.assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
	}

	@Test
	fun goodRequestReturnsWithoutError() {
		val goodRequest = "?title=ExampleItem&sender=user12345&category=items&price=2.99"
		val result = testRestTemplate.getForEntity("/checkAccess$goodRequest", String::class.java)
		//println(result.body)
		Assertions.assertNotNull(result.body)
	}

}
