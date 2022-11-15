package com.dishIT.seatbooking.activities

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.GetFloors
import com.dishIT.seatbooking.model.GetSeats
import com.dishIT.seatbooking.viewModel.GetFloorsViewModel
import com.dishIT.seatbooking.viewModel.seatScheduleViewModel
import com.example.seatbooking.R
import com.example.seatbooking.databinding.ActivitySeatsBookingBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class SeatsBookingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySeatsBookingBinding
    var spinner : Spinner? = null
    var monday = false
    var tuesday= false
    var wednesday= false
    var thursday= false
    var friday= false
    var floors = ArrayList<Int>()
    var selectedFloor :Int = 1
    var selectedSeat: Int? = null
    private var selectedToDate by Delegates.notNull<Long>()
    private var maxToDate by Delegates.notNull<Long>()
    var endDate: String? = null
    lateinit var token :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeatsBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        token = AppPreferences(this).token
        binding.moreDays.setOnCheckedChangeListener { compoundButton, b ->
            if (!b)
                endDate = null
            binding.endsDate.isVisible = b
        }
        binding.backArrow.setOnClickListener {
            onBackPressed()
        }
        setLocation()
        recurringDays()
        isRecurring()
        getFloors()
        binding.startDate.setOnClickListener {
            getFromDate(binding.startDate)
        }
        binding.endDate.setOnClickListener {
            getToDate(binding.endDate)

        }
        binding.floorMap.setOnClickListener {
            binding.floorMapView.isVisible =true
            binding.floorMapView.isClickable =true
            binding.floorMapView.isFocusable =true
        }
        binding.map.close.setOnClickListener {
            binding.floorMapView.isVisible =false
            binding.floorMapView.isClickable =false
            binding.floorMapView.isFocusable =false
        }
        binding.checkBtn.setOnClickListener {

            val intent  = Intent(this, AvailableSeats::class.java)
            if (endDate.isNullOrEmpty())
                endDate = binding.startDate.text as String
            intent.putExtra("endDate",endDate)
            intent.putExtra("startDate", binding.startDate.text as String)
            intent.putExtra("floor",selectedFloor)
            intent.putExtra("seat",selectedSeat!!)
            startActivity(intent)
        }
    }

    fun isRecurring() {
        binding.recurringSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            binding.weekDays.isVisible = isChecked

        }
    }


    fun recurringDays(){

        binding.mon.setOnClickListener {
            monday = !monday
            setBackground(binding.mon,monday)
        }

        binding.tue.setOnClickListener {
            tuesday = !tuesday
            setBackground(binding.tue,tuesday)
        }

        binding.wed.setOnClickListener {
            wednesday  =!wednesday
            setBackground(binding.wed, wednesday)
        }
        binding.thur.setOnClickListener {
            thursday  =!thursday
            setBackground(binding.thur, thursday)
        }

        binding.fri.setOnClickListener {
            friday  =!friday
            setBackground(binding.fri, friday)
        }
    }

    private fun setBackground(text: TextView, bool: Boolean){
        text.setBackgroundResource(
            if (bool)
                R.drawable.ic_bg_selected
            else
                R.drawable.ic_bg_not_selected
        )
    }

    private fun getSeats() {

        val getSeatsViewModel by viewModels<seatScheduleViewModel>()
        Log.e("jjgugkj",selectedFloor.toString())
        getSeatsViewModel.getSeatSchedule(token, "$selectedFloor")
        getSeatsViewModel.apiCaller.observe(
            this
        ) { data ->
            if (data is GetSeats) {
                val showSeats = ArrayList<Int>()
                data.forEach {
                    showSeats.add(it.seatName)
                }
                setSeats(showSeats)
                Log.e("bjhgjkgkjg",showSeats.toString())
            }
        }
    }

    private fun getFloors(){
        val getFloorsViewModel by viewModels<GetFloorsViewModel>()
        getFloorsViewModel.getFloors(token)
        getFloorsViewModel.apiCaller.observe(
            this
        ){ data->
            if(data is GetFloors){
                data.forEach {
                    floors.add(it.floorName)
                }
                setFloors()
            }
        }
    }

    fun setFloors(){
        val floorAdapter = ArrayAdapter<Int>(this,R.layout.spinner_text, floors)
        floorAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        binding.floor.adapter = floorAdapter
        binding.floor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedFloor = floors[position]
                getSeats()
            }

        }
    }

    private fun setLocation(){
        spinner = binding.location
        val locations = arrayOf("Bangalore", "Hyderabad")
        val langAdapter = ArrayAdapter<CharSequence>(this, R.layout.spinner_text, locations)
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner?.adapter = langAdapter
    }
    fun setSeats(showSeats: ArrayList<Int>) {
        spinner  = binding.seat
        Log.e("sdsqkdqksd",showSeats.toString())
        val seatAdapter = ArrayAdapter<Int>(this,R.layout.spinner_text, showSeats)
        seatAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        spinner?.adapter = seatAdapter
        binding.seat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSeat = showSeats[position]
            }

        }
        binding.checkBtn.isEnabled = true
    }

    private fun getFromDate(textView: TextView){
        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                selectedToDate = cal.timeInMillis
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.minDate = cal.timeInMillis - 1000
        cal.add(Calendar.MONTH,1)
        maxToDate = cal.timeInMillis
        datePickerDialog.datePicker.maxDate = maxToDate
        datePickerDialog.show()
    }

    private fun getToDate(textView: TextView){
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "yyyy-MM-dd"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                textView.text = sdf.format(cal.time)
                endDate = sdf.format(cal.time)
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.datePicker.minDate = this.selectedToDate
        datePickerDialog.datePicker.maxDate = this.maxToDate
        datePickerDialog.show()
    }

}