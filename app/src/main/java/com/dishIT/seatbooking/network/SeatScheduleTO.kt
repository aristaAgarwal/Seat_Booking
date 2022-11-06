package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.GetAvailableSeatsDO
import com.dishIT.seatbooking.model.GetAvailableSeatsResponseDO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SeatScheduleTO {

    @POST("getavailableseats")
    suspend fun getAvailableSeats(
        @Header("Authorization") token: String,
        @Body requestTO: GetAvailableSeatsDO
    ): Response<GetAvailableSeatsResponseDO>
}