package com.dishIT.seatbooking.model

data class GetAvailableSeatsResponseItem(
    val available: Boolean,
    val dataList: List<String>,
    val scheduleBookingList: Any,
    val seat: Seat
)