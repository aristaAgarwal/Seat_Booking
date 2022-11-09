package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.DeleteBookingDO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface DeleteBooking {
    @POST("leaveupdate")
    suspend fun deleteBooking(
        @Header("Authorization") token: String,
        @Body requestTo : DeleteBookingDO
    ):Response<String>
}