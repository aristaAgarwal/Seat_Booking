package com.dishIT.seatbooking.activities

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.seatbooking.R
import com.example.seatbooking.databinding.ActivityMeetingRoomBinding

import java.util.*

class MeetingRoomActivity : AppCompatActivity() {
    lateinit var binding: ActivityMeetingRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        setCapacity()
        binding.fromTimePicker.setOnClickListener {
            pickFromTime(binding.fromTimePicker)
        }
        binding.toTimePicker.setOnClickListener {
            pickToTime(binding.toTimePicker)
        }
    }

    private fun setCapacity(){
        val locations = arrayOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30")
        val langAdapter = ArrayAdapter<CharSequence>(this, R.layout.spinner_text, locations)
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        binding.selectCapacitySpinner.adapter = langAdapter
    }

    private fun pickFromTime(textView: TextView){
            val c = Calendar.getInstance()

            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)
            val timePickerDialog = TimePickerDialog(
                this,
                { view, hourOfDay, minute ->
                    textView.setText("$hourOfDay:$minute")
                },
                hour,
                minute,
                false
            )
            timePickerDialog.show()
    }

    private fun pickToTime(textView: TextView){
        val c = Calendar.getInstance()

        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { view, hourOfDay, minute ->

                textView.setText("$hourOfDay:$minute")
            },
            hour,
            minute,
            false
        )
        timePickerDialog.show()
    }

}