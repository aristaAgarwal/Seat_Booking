package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.AvailableDatesResponse
import com.dishIT.seatbooking.model.SeatAvailableDates
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SeatAvailableDatesTO {
    @POST("v2/getavailableseats")
    suspend fun getSeatAvailableDates(
        @Header("Authorization") token: String,
        @Body requestTO: SeatAvailableDates
    ):Response<AvailableDatesResponse>
}