package com.dishIT.seatbooking.model

data class GetSeatsItem(
    val floorName: Int,
    val id: String,
    val lockerName: Any,
    val seatName: Int
)