package com.dishIT.seatbooking.activities

import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dishIT.seatbooking.adapter.MyBookingsAdapter
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.MyBookings
import com.dishIT.seatbooking.viewModel.MyBookingsVM
import com.example.seatbooking.databinding.ActivityMyBookingsBinding

class MyBookingsActivity : AppCompatActivity() {
    lateinit var binding : ActivityMyBookingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyBookingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnClickListener()
        getMyBookings()
    }

    private fun getMyBookings() {
        val myBookingVM  by viewModels<MyBookingsVM>()
        myBookingVM.myBookings(AppPreferences(this).token)
        myBookingVM.apiCaller.observe(
            this
        ){data->
            if(data is MyBookings){
                setMyBookingAdapter(data)
            }
        }
    }

    fun setMyBookingAdapter(data: MyBookings) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.myBookings.layoutManager = layoutManager
        binding.myBookings.adapter = MyBookingsAdapter(this, data)

    }


    private fun setOnClickListener() {
        binding.btn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}