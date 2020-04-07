package com.shipping_eligibility

data class EligibilityRequest(
        val title: String,
        val seller: String,
        val category: String,
        val price: Double
)