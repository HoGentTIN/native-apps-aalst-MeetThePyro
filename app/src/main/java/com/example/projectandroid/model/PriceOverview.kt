package com.example.projectandroid.model

data class PriceOverview2(
    var currency: String,
    var initial: Int,
    var final: Int,
    var discount_percent: Int,
    var initial_formatted: String,
    var final_formatted: String
)
