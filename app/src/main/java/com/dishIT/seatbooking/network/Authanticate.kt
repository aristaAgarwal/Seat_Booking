package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.AuthenticationDO
import com.dishIT.seatbooking.model.AuthenticationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface Authanticate {
    @POST("authenticate")
    suspend fun authenticate(
        @Body resquestTO: AuthenticationDO
    ): Response<AuthenticationResponse>
}