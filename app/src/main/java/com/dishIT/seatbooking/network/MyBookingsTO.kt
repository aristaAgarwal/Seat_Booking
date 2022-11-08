package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.MyBookings
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface MyBookingsTO {
    @GET("mybookings")
    suspend fun getMyBookings(
        @Header("Authorization") token: String
    ):Response<MyBookings>
}