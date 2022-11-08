package com.dishIT.seatbooking.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.seatbooking.R
import com.example.seatbooking.databinding.ActivityEditPremisesBinding

class EditPremisesActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditPremisesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditPremisesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setFloors()
    }
    private fun setFloors(){
        val locations = arrayOf("1", "2","3","4")
        val langAdapter = ArrayAdapter<CharSequence>(this, R.layout.spinner_text, locations)
        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        binding.selectFloorSpinner.adapter = langAdapter
    }
}