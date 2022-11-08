package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.MyBookings
import com.dishIT.seatbooking.network.MyBookingsTO
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class MyBookingsVM: ViewModel() {
    val api: MyBookingsTO = RetrofitService().myBookings
    private val _apiCaller = MutableLiveData<MyBookings>()
    val apiCaller: LiveData<MyBookings>
        get() = _apiCaller

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
}