package com.dishIT.seatbooking.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.AuthenticationDO
import com.dishIT.seatbooking.model.AuthenticationResponse
import com.dishIT.seatbooking.viewModel.LoginViewModel
import com.example.seatbooking.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    var username :String? = null
    var password :String? = null
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCredentials()
    }

    fun getCredentials(){
        username = binding.name.text.toString()
        password = binding.pass.text.toString()
        if(username != null && password!= null)
        authenticate(username!!, password!!)
    }

    fun authenticate(username: String, password: String) {
        val loginCred = AuthenticationDO(password, username)
        val authenticate by viewModels<LoginViewModel>()
        authenticate.login(loginCred)
        authenticate.apiCaller.observe(
            this
        ){
                data->
            if(data is AuthenticationResponse){
                AppPreferences(this).token  = "Bearer "+data.id_token
            }
        }
        startActivity(Intent(this, HomeActivity::class.java))
    }
}