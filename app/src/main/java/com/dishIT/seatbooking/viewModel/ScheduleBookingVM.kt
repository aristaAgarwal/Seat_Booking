package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.GetAvailableSeatsDO
import com.dishIT.seatbooking.model.GetAvailableSeatsResponseDO
import com.dishIT.seatbooking.model.ScheduleBooking
import com.dishIT.seatbooking.model.ScheduleBookingResponse
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class ScheduleBookingVM: ViewModel() {
    val api = RetrofitService().scheduleBooking
    private val _apiCaller = MutableLiveData<ScheduleBookingResponse>()
    val apiCaller: LiveData<ScheduleBookingResponse>
        get() = _apiCaller

    fun bookSchedule(token:String ,requestTO: ScheduleBooking){
        viewModelScope.launch {
            try{
                val result = api.scheduleBooking(token, requestTO)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("ViewModel", e.toString())
            }
        }
    }
}