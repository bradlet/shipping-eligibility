package com.shipping_eligibility

// Holds all information associated with a request to check shipping program eligibility.
// Also ensures valid data, and could hold data persistence functionality.
class EligibilityRequest(private val title: String, private val seller: String,
                         private val category: Int, private val price: Double
) {
    // Throws an exception if params are blank, or if a negative price was supplied.
    fun validate(){
        if (title.isBlank() || (seller.isBlank()) || category < 0 ||
            price.isNaN() || price < 0) {
            println("throw InvalidInputException")
            throw InvalidInputException("BAD REQUEST: $title, $seller, $category, $price")
        }
    }

    // Run all checks to determine if this request is
    // eligible to access the shipping program
    fun checkRequest(sellerList: Array<String>, categoryList: Array<Int>,
                     minPrice: Double): Boolean {
        if (checkSeller(sellerList) && checkCategory(categoryList) && checkPrice(minPrice))
            return true
        return false
    }

    // Return true if this EligibilityRequest's seller is in the list supplied
    fun checkSeller(list: Array<String>): Boolean {
        if (seller in list)
            return true
        return false
    }

    // Return true if this EligibilityRequest's category is in the list supplied
    fun checkCategory(list: Array<Int>): Boolean {
        if (category in list)
            return true
        return false
    }

    // Return true if this EligibilityRequest's price is >= supplied minimum price
    fun checkPrice(minPrice: Double): Boolean {
        if (price >= minPrice)
            return true
        return false
    }
}
