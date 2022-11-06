package com.dishIT.seatbooking.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.GetFloors
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class GetFloorsViewModel : ViewModel(){
    val api = RetrofitService().getfloors
    private val _apiCaller = MutableLiveData<GetFloors>()
    val apiCaller: LiveData<GetFloors>
        get() = _apiCaller

    fun getFloors(token: String){
        viewModelScope.launch {
            try{
                val result = api.getFloors(token)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("SeatViewModel", e.toString())

            }
        }
    }
}