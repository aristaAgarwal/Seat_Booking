package com.dishIT.seatbooking.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dishIT.seatbooking.adapter.SeatAvailableDateAdapter
import com.dishIT.seatbooking.adapter.SeatBookedDateAdapter
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.*
import com.dishIT.seatbooking.viewModel.ScheduleBookingVM
import com.dishIT.seatbooking.viewModel.SeatAvailDatesVM
import com.example.seatbooking.databinding.ActivityAvailableSeatsBinding

class AvailableSeats : AppCompatActivity(), SeatAvailableDateAdapter.AppLinkClick, SeatBookedDateAdapter.AppLinkClick {
    var floor: Int? =null
    var seat: Int? =null
    var startDate: String? =null
    var endDate: String? =null
    var availableDates = mutableListOf<String>()
    var bookedDates = mutableSetOf<String>()
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

    @SuppressLint("SetTextI18n")
    private fun init(){
        binding.backArrow.setOnClickListener {
            onBackPressed()
        }
        binding.btn.setOnClickListener {
            scheduleBooking()
            binding.successPopup.seatNo.text = "F"+floor.toString() +"-"+ seat.toString()
            setPopup(true,binding.bookingConfirmLayout)
        }
        binding.successPopup.cross.setOnClickListener {
            setPopup(false, binding.bookingConfirmLayout)
        }
        binding.successPopup.btn.setOnClickListener {
            setPopup(false, binding.bookingConfirmLayout)
        }
        binding.requestPopup.cross.setOnClickListener {
            setPopup(false, binding.requestSeatLayout)
        }
        binding.requestPopup.cancelButton.setOnClickListener {
            setPopup(false, binding.requestSeatLayout)
        }
        binding.requestPopup.submit.setOnClickListener {
            setPopup(false, binding.requestSeatLayout)
        }
    }
    fun scheduleBooking(){
        val scheduleBookingVM by viewModels<ScheduleBookingVM>()
        val pref = AppPreferences(this)
        val bookedDates  = bookedDates.toString().replace("[","").replace("]","")
        val scheduleBooking = ScheduleBooking(pref.empId,pref.userName,floor.toString(),"DAY",1,1,seat.toString(),bookedDates)
        scheduleBookingVM.bookSchedule(pref.token,scheduleBooking)
        scheduleBookingVM.apiCaller.observe(
            this
        ){data  ->
            if(data is ScheduleBookingResponse){
                Log.e("chjbvckljdce", data.toString())
            }
        }
    }
    private fun getAvailableDates(){
        val availableDatesVM by viewModels<SeatAvailDatesVM>()
        val bookedDatesResponse = mutableListOf<AvailableDatesResponseItem>()
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
            bookedDatesResponse,
            this
        )
    }

    fun setAvailableSeatAdapter(availableDatesResponse: MutableList<String>) {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.AvailableSeats.layoutManager = layoutManager
        binding.AvailableSeats.adapter = SeatAvailableDateAdapter(
            this,
            availableDatesResponse,
            this
        )
    }

    override fun onAppLinkClicked(isChecked: Boolean, date: String) {
        if (isChecked)
            bookedDates.add(date)
        else
            bookedDates.remove(date)
            binding.btn.isEnabled = (bookedDates.size>0)

    }

    override fun onAppLinkClicked(id: String, name: String) {
        binding.requestPopup.ownerName.text = name
        binding.requestPopup.empid.text = id
        setPopup(true, binding.requestSeatLayout)
    }

    private fun setPopup(b: Boolean, layout: RelativeLayout){

        layout.isVisible = b
        layout.isClickable = b
        layout.isFocusable = b
    }
}