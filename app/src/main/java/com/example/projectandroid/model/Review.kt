package com.example.projectandroid.model

import java.util.*

data class Review(
    var id: Int,
    var creator: User,
    var date: Date,
    var desc: String
)
