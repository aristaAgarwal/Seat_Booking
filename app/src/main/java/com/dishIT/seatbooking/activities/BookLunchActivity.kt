package com.dishIT.seatbooking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import com.example.seatbooking.R

class BookLunchActivity : AppCompatActivity() {
    lateinit var parkingCheckBox: CheckBox
    lateinit var foodCheckBox: CheckBox
    lateinit var parkingRadioButtons: RadioGroup
    lateinit var chooseMeal: LinearLayout
    lateinit var radio4Wheeler: RadioButton
    lateinit var radio2Wheeler: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_lunch)

        parkingCheckBox = findViewById(R.id.parking_checkbox)
        foodCheckBox = findViewById(R.id.food_checkbox)
        parkingRadioButtons = findViewById(R.id.parking_radio_buttons)
        chooseMeal = findViewById(R.id.choose_meal)
        radio4Wheeler = findViewById(R.id.radio_4wheeler)
        radio2Wheeler = findViewById(R.id.radio_2wheeler)

        // on below line we are adding check change listener for our radio group.
        parkingRadioButtons.setOnCheckedChangeListener{ group, checkedId ->
            val radioButton: RadioButton = group.findViewById(checkedId)
            Log.e("selectedtext-->",radioButton.text.toString())
        }

        parkingCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            parkingRadioButtons.isVisible = b
        }

        foodCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            chooseMeal.isVisible = b
        }
    }
}