package com.example.abumax.entity

data class Order(
    val name: String? = null,
    val price: String? = null,
    var count: Int? = null,
    var totalPrice: Int? = null
)
