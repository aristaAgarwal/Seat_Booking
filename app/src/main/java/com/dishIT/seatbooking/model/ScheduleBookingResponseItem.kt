package com.dishIT.seatbooking.model

data class ScheduleBookingResponseItem(
    val comments: Any,
    val empId: String,
    val empName: String,
    val endDate: Any,
    val floorName: Int,
    val id: String,
    val lockerName: Any,
    val messageData: String,
    val occurrenceDate: String,
    val recurCalendarPattern: Any,
    val recuringCount: Any,
    val recuringFreqDelay: Any,
    val seatName: Int,
    val startDate: Any,
    val startMonthsPattern: Any,
    val weekDaysPattern: Any
)