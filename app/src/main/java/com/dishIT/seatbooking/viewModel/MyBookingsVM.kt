package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.DeleteBookingDO
import com.dishIT.seatbooking.model.MyBookings
import com.dishIT.seatbooking.network.DeleteBooking
import com.dishIT.seatbooking.network.MyBookingsTO
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class MyBookingsVM: ViewModel() {
    val api: MyBookingsTO = RetrofitService().myBookings
    val delapi: DeleteBooking = RetrofitService().deleteBooking
    private val _apiCaller = MutableLiveData<MyBookings>()
    val apiCaller: LiveData<MyBookings>
        get() = _apiCaller
    private val _dapiCaller = MutableLiveData<String>()
    val dapiCaller: LiveData<String>
        get() = _dapiCaller

    fun myBookings(token: String){
        viewModelScope.launch {
            try{
                val result = api.getMyBookings(token)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("SeatViewModel", e.toString())

            }
        }
    }
    fun deleteBookings(token: String, delete: DeleteBookingDO){
        viewModelScope.launch {
            try{
                val result = delapi.deleteBooking(token,delete)
                _dapiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("SeatViewModel", e.toString())

            }
        }
    }
}