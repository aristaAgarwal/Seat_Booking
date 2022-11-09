package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.RegisterAccount
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Register {
    @POST("register")
    suspend fun register(
        @Body requestTO: Register
    ):Response<RegisterAccount>
}