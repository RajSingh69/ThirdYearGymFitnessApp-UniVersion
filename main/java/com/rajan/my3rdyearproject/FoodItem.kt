package com.rajan.my3rdyearproject

/**
 * Data class food item for 3rd year project.
 * I am making sure I take all values in as strings from firebase.
 * @author Rajan Singh Bhamra (2034215)
 * @version 2
 */

data class FoodItem(
    val collectionId: String,
    val documentId: String,
    val field1: String,
    val field2: String,
    val field3: String
)