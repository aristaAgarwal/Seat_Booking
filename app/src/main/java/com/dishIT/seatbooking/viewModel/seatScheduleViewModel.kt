package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.GetSeats
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class seatScheduleViewModel: ViewModel() {
    val api = RetrofitService().seatSchedule
    private val _apiCaller = MutableLiveData<GetSeats>()
    val apiCaller: LiveData<GetSeats>
        get() = _apiCaller

    fun getSeatSchedule(token:String ,floor: String){
        viewModelScope.launch {
            try{
                val result = api.getAvailableSeats(token, floor)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("ViewModel", e.toString())
            }
        }
    }
}