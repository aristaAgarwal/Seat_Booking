package com.dishIT.seatbooking.network

import com.dishIT.seatbooking.model.GetFloors
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GetFloorsTO {
    @GET("floors")
    suspend fun getFloors(
        @Header("Authorization") token: String
    ):Response<GetFloors>
}