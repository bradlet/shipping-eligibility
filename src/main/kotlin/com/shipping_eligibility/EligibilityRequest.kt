package com.shipping_eligibility

// Holds all information associated with a request to check shipping program eligibility.
// Also ensures valid data, and could hold data persistence functionality.
class EligibilityRequest(
        val title: String,
        val seller: String,
        val category: String,
        val price: Double
) {
    // Throws an exception if params are blank, or if a negative price was supplied.
    fun validate(){
        if (title.isBlank() || (seller.isBlank()) || category.isBlank() ||
            price.isNaN() || price < 0) {
            println("throw InvalidInputException")
            throw InvalidInputException("BAD REQUEST: $title, $seller, $category, $price")
        }
    }
}