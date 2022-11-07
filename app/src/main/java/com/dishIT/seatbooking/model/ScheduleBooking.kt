package com.dishIT.seatbooking.model

data class ScheduleBooking(
    val empId: String,
    val empName: String,
    val floorName: String,
    val recurCalendarPattern: String?,
    val recuringCount: Int?,
    val recuringFreqDelay: Int?,
    val seatName: String,
    val startDate: String
)