package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.GetSeats
import retrofit2.Response
import retrofit2.http.*

interface SeatScheduleTO {

    @GET("getseats/{path}")
    suspend fun getAvailableSeats(
        @Header("Authorization") token: String,
        @Path("path") path: String
    ): Response<GetSeats>
}