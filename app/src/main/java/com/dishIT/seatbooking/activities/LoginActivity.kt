package com.dishIT.seatbooking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.AuthenticationDO
import com.dishIT.seatbooking.model.AuthenticationResponse
import com.dishIT.seatbooking.model.RegisterAccount
import com.dishIT.seatbooking.viewModel.LoginViewModel
import com.dishIT.seatbooking.viewModel.RegisterVM
import com.example.seatbooking.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCredentials()
        init()
    }

    fun init(){

        binding.signUp.setOnClickListener {
            binding.signIn.isVisible = false
            binding.detail.isVisible = false
            binding.register.isVisible = true
            var username = binding.newName.text
            val empId = binding.empid.text
            val email = binding.email.text
            val pass = binding.newPassConf.text
            registerAccount(username as String,empId as String,email as String,pass as String)
        }
    }

    fun registerAccount(username: String, empId: String, email: String, pass: String) {
        val registerDO = RegisterAccount(email,username,empId,username, pass)
        val registerVM by viewModels<RegisterVM>()
    }

    fun getCredentials(){
        val username = binding.name.text
        val password = binding.pass.text
        binding.btn.setOnClickListener {
            authenticate(username.toString(), password.toString())
        }
    }

    fun authenticate(username: String, password: String) {
        Log.e("jjvj","aa gaye hai  gaya")
        Log.e("jjvj","$username $password")
        val loginCred = AuthenticationDO(password, username)
        val authenticate by viewModels<LoginViewModel>()
        authenticate.login(loginCred)
        authenticate.apiCaller.observe(
            this
        ){
                data->
            if(data is AuthenticationResponse){
                AppPreferences(this).token  = "Bearer "+data.id_token
                Log.e("jjvj","ho gaya")
                AppPreferences(this).firstLaunch = false
                finish()
            }
        }
    }
}