package com.dishIT.seatbooking.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.AuthenticationDO
import com.dishIT.seatbooking.model.AuthenticationResponse
import com.dishIT.seatbooking.model.GetAccount
import com.dishIT.seatbooking.model.RegisterAccount
import com.dishIT.seatbooking.viewModel.GetAccountVM
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
            binding.seatBooking.isVisible = false
            binding.detail.isVisible = false
            binding.register.isVisible = true
            var username = binding.newName.text
            val empId = binding.empid.text
            val email = binding.email.text
            val pass = binding.newPassConf.text
            binding.registerBtn.setOnClickListener {
                registerAccount(username,empId ,email ,pass )
            }
        }
    }

    fun registerAccount(username: Editable, empId: Editable, email: Editable, pass: Editable) {
        val registerDO = RegisterAccount(email.toString(),username.toString(),empId.toString(),username.toString(), pass.toString())
        val registerVM by viewModels<RegisterVM>()
        registerVM.register(registerDO)
        binding.signIn.isVisible = true
        binding.detail.isVisible = true
        binding.seatBooking.isVisible = true
        binding.register.isVisible = false

    }

    fun getCredentials(){
        val username = binding.name.text
        val password = binding.pass.text
        binding.btn.setOnClickListener {
            Toast.makeText(this,"Authenticating",Toast.LENGTH_SHORT).show()
            authenticate(username.toString(), password.toString())
        }
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
                getAccount()
                AppPreferences(this).firstLaunch = false
                finish()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }
    fun getAccount(){
        val getAccount by viewModels<GetAccountVM>()
        val pref = AppPreferences(this)
        getAccount.getAccount(pref.token)
        getAccount.apiCaller.observe(
            this
        ){data->
            if(data is GetAccount){
                pref.userName = data.login
                pref.empId = data.id
                pref.emailId = data.email
                pref.authorities = if(data.authorities.size > 1)
                    data.authorities[1]
                else
                    data.authorities[0]
            }
        }
    }
}