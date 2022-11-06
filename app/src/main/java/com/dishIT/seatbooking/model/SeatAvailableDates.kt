package com.dishIT.seatbooking.model

data class SeatAvailableDates(
    val endDate: String,
    val floorName: Int,
    val seatName: Int,
    val startDate: String
)