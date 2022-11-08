package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.GetAccount
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class GetAccountVM: ViewModel() {
    val api = RetrofitService().getAccount
    private val _apiCaller = MutableLiveData<GetAccount>()
    val apiCaller: LiveData<GetAccount>
        get() = _apiCaller

    fun getAccount(token: String){
        viewModelScope.launch {
            try{
                val result = api.getAccount(token)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("SeatViewModel", e.toString())

            }
        }
    }
}