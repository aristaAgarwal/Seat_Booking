package com.dishIT.seatbooking.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dishIT.seatbooking.model.AuthenticationDO
import com.dishIT.seatbooking.model.AuthenticationResponse
import com.dishIT.seatbooking.network.RetrofitService
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    val api = RetrofitService().getAuthentication
    private val _apiCaller = MutableLiveData<AuthenticationResponse>()
    val apiCaller: LiveData<AuthenticationResponse>
        get() = _apiCaller

    fun login(cred: AuthenticationDO){
        val token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY2NTQ4MjU3MH0.VMRE4gJWex1s1SNSlnZqnTP7lh-aYka85glUSNFzqFoLSkXyifWTiefGKmb5HaKsnZrO37rMOy6v3h5yrmAsSQ"
        viewModelScope.launch {
            try{
                val result = api.authenticate(cred)
                _apiCaller.postValue(result.body())
            }
            catch (e: Exception){
                Log.e("SeatViewModel", e.toString())

            }
        }
    }
}