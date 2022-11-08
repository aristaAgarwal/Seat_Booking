package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.GetAccount
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GetAccount {
    @GET("account")
    suspend fun getAccount(
        @Header("Authorization") token: String
    ):Response<GetAccount>
}