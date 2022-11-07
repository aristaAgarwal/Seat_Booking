package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.ScheduleBooking
import com.dishIT.seatbooking.model.ScheduleBookingResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PostScheduleBooking {
    @POST("schedule-bookings")
    suspend fun scheduleBooking(
        @Header("Authorization") token: String,
        @Body requestTO: ScheduleBooking
    ): Response<ScheduleBookingResponse>
}