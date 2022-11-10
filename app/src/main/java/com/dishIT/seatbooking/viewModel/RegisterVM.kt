package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.RegisterAccount
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class RegisterVM: ViewModel() {
    val api = RetrofitService().register

    fun register(requestTO: RegisterAccount){
        viewModelScope.launch {
            try{
                api.register(requestTO)
            }
            catch (e: Exception){
                Log.e("ViewModel", e.toString())
            }
        }
    }
}