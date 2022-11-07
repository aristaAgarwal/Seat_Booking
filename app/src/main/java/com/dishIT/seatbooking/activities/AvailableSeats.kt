package com.dishIT.seatbooking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dishIT.seatbooking.adapter.SeatAvailableDateAdapter
import com.dishIT.seatbooking.adapter.SeatBookedDateAdapter
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.AvailableDatesResponse
import com.dishIT.seatbooking.model.AvailableDatesResponseItem
import com.dishIT.seatbooking.model.SeatAvailableDates
import com.dishIT.seatbooking.viewModel.SeatAvailDatesVM
import com.example.seatbooking.databinding.ActivityAvailableSeatsBinding

class AvailableSeats : AppCompatActivity() {
    var floor: Int? =null
    var seat: Int? =null
    var startDate: String? =null
    var endDate: String? =null
    var availableDates = mutableListOf<String>()
    var bookedDates = mutableListOf<String>()
    lateinit var binding: ActivityAvailableSeatsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvailableSeatsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val i = intent
        floor = i.getIntExtra("floor",1)
        seat = i.getIntExtra("seat",1)
        startDate = i.getStringExtra("startDate")
        endDate = i.getStringExtra("endDate")

        init()
        getAvailableDates()
    }

    private fun init(){
        binding.backArrow.setOnClickListener {
            onBackPressed()
        }
    }
    private fun getAvailableDates(){
        val availableDatesVM by viewModels<SeatAvailDatesVM>()
        var bookedDatesResponse = mutableListOf<AvailableDatesResponseItem>()
        val seatAvailableDates = SeatAvailableDates(endDate!!,floor!!,seat!!,startDate!!)

        availableDatesVM.getSeatSchedule(AppPreferences(this).token, seatAvailableDates)
        availableDatesVM.apiCaller.observe(
            this
        ){ data->
            if(data is AvailableDatesResponse){
                data.forEach{
                    if(it.available)
                        availableDates.add(it.date)
                    else {
                        bookedDatesResponse.add(it)
                    }
                }
                setAvailableSeatAdapter(availableDates)
                setBookedSeatAdapter(bookedDatesResponse)
            }
        }


    }

    fun setBookedSeatAdapter(bookedDatesResponse: MutableList<AvailableDatesResponseItem>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.BookedSeats.layoutManager = layoutManager
        binding.BookedSeats.adapter = SeatBookedDateAdapter(
            this,
            bookedDatesResponse
        )
    }

    fun setAvailableSeatAdapter(availableDatesResponse: MutableList<String>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.AvailableSeats.layoutManager = layoutManager
        binding.AvailableSeats.adapter = SeatAvailableDateAdapter(
            this,
            availableDatesResponse
        )
    }
}