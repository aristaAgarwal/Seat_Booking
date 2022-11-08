package com.dishIT.seatbooking.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.seatbooking.R
import com.example.seatbooking.databinding.ActivityClearBookingsBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class ClearBookingsActivity : AppCompatActivity() {
    lateinit var binding: ActivityClearBookingsBinding
    private var maxDate by Delegates.notNull<Long>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClearBookingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        setFloors()
        binding.date.setOnClickListener {
            getDate(binding.date)
        }
    }

    private fun setFloors(){
        val locations = arrayOf("1", "2","3","4")
        val langAdapter = ArrayAdapter<CharSequence>(this, R.layout.spinner_text, locations)
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        binding.floors.adapter = langAdapter
    }

    private fun getDate(textView: TextView){
        val cal = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->

                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
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
        maxDate = cal.timeInMillis
        datePickerDialog.datePicker.maxDate = maxDate
        datePickerDialog.show()
    }
}