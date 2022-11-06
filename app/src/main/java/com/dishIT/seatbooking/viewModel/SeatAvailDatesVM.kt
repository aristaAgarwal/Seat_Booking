package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.AvailableDatesResponse
import com.dishIT.seatbooking.model.SeatAvailableDates
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class SeatAvailDatesVM: ViewModel() {
    val api = RetrofitService().getSeatAvailableDates
    private val _apiCaller = MutableLiveData<AvailableDatesResponse>()
    val apiCaller: LiveData<AvailableDatesResponse>
        get() = _apiCaller

    fun getSeatSchedule(token:String ,requestTO: SeatAvailableDates){
        viewModelScope.launch {
            try{
                val result = api.getSeatAvailableDates(token,requestTO)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("ViewModel", e.toString())
            }
        }
    }
}