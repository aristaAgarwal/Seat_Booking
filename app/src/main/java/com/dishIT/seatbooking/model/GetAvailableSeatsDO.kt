package com.dishIT.seatbooking.model

data class GetAvailableSeatsDO(
    val startDate: String,
    val floorName: Int,
    val endDate: String
)