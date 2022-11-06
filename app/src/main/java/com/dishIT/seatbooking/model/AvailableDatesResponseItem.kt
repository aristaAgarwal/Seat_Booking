package com.dishIT.seatbooking.model

data class AvailableDatesResponseItem(
    val available: Boolean,
    val bookedUser: Any,
    val date: String
)