package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.GetAvailableSeatsDO
import com.dishIT.seatbooking.model.GetAvailableSeatsResponseDO
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class seatScheduleViewModel: ViewModel() {
    val api = RetrofitService().seatSchedule
    private val _apiCaller = MutableLiveData<GetAvailableSeatsResponseDO>()
    val apiCaller: LiveData<GetAvailableSeatsResponseDO>
        get() = _apiCaller

    fun getSeatSchedule(token:String ,requestTO: GetAvailableSeatsDO){
        viewModelScope.launch {
            try{
                val result = api.getAvailableSeats(token, requestTO)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("ViewModel", e.toString())
            }
        }
    }
}