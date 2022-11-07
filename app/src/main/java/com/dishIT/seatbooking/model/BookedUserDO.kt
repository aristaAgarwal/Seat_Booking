package com.dishIT.seatbooking.model

data class BookedUserDO(
    val empId: String,
    val empName: String,
    val floorName: Int,
    val seatName: Int
)