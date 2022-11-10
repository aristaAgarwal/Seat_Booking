package com.dishIT.seatbooking.activities

import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dishIT.seatbooking.adapter.MyBookingsAdapter
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.DeleteBookingDO
import com.dishIT.seatbooking.model.MyBookings
import com.dishIT.seatbooking.viewModel.MyBookingsVM
import com.example.seatbooking.databinding.ActivityMyBookingsBinding

class MyBookingsActivity : AppCompatActivity(), MyBookingsAdapter.AppLinkClick {
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
        binding.myBookings.adapter?.notifyDataSetChanged()
        binding.myBookings.adapter = MyBookingsAdapter(this, data, this)

    }


    private fun setOnClickListener() {
        binding.btn.setOnClickListener {
            finish()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    override fun onAppLinkClicked(id: String, date: String) {
        binding.deleteBookingLayout.isClickable = true
        binding.deleteBookingLayout.isVisible = true
        binding.deleteBookingLayout.isFocusable = true
        binding.deletePopup.submit.setOnClickListener {
            val myBookingVM  by viewModels<MyBookingsVM>()
            val deleteBookinDO = DeleteBookingDO(id,date)
            myBookingVM.deleteBookings(AppPreferences(this).token,deleteBookinDO)
            myBookingVM.dapiCaller.observe(
                this
            ){data->

            }
            getMyBookings()
            binding.deleteBookingLayout.isClickable = false
            binding.deleteBookingLayout.isVisible = false
            binding.deleteBookingLayout.isFocusable = false
        }

        binding.deletePopup.cancelButton.setOnClickListener {
            binding.deleteBookingLayout.isClickable = false
            binding.deleteBookingLayout.isVisible = false
            binding.deleteBookingLayout.isFocusable = false
        }

    }
}