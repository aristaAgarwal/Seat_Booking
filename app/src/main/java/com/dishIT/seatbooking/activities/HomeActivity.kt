package com.dishIT.seatbooking.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import com.example.seatbooking.R
import kotlin.properties.Delegates
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.activity.viewModels
import com.dishIT.seatbooking.constants.AppPreferences
import com.dishIT.seatbooking.model.AuthenticationDO
import com.dishIT.seatbooking.model.AuthenticationResponse
import com.dishIT.seatbooking.viewModel.LoginViewModel
import com.dishIT.seatbooking.viewModel.seatScheduleViewModel
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    var spinner :Spinner? = null
    private val seatScheduleViewModel by viewModels<seatScheduleViewModel>()
    private lateinit var fromDate: TextView
    private lateinit var toDate: TextView
    private var selectedToDate by Delegates.notNull<Long>()
    private var maxToDate by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setNavigationMenu()
        init()
        navigationView = findViewById(R.id.navigation_view)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_myaccount -> {
                    startActivity(Intent(this, MyAccountActivity::class.java))
                    true
                }
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_clear_bookings -> {
                    startActivity(Intent(this, ClearBookingsActivity::class.java))
                    true
                }
                R.id.nav_mybookings -> {
                    startActivity(Intent(this, MyBookingsActivity::class.java))
                    true
                }
                R.id.nav_edit_premises -> {
                    startActivity(Intent(this, EditPremisesActivity::class.java))
                    true
                }
                R.id.nav_feedback -> {
                    startActivity(Intent(this, FeedbackActivity::class.java))
                    true
                }
                else -> false

            }
        }
    }

    private fun setNavigationMenu(){

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // override the onOptionsItemSelected() function to implement the item click listener callback
    // to open and close the navigation drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun init(){
        val seatBooking = findViewById<View>(R.id.seat_booking)
        seatBooking.setOnClickListener {
            startActivity(Intent(this, SeatsBookingActivity::class.java))
        }
        authenticate()
    }
//
//    private fun getFromDate(textView: TextView){
//        val cal = Calendar.getInstance()
//        val datePickerDialog = DatePickerDialog(
//            this,
//            { _, year, monthOfYear, dayOfMonth ->
//
//                cal.set(Calendar.YEAR, year)
//                cal.set(Calendar.MONTH, monthOfYear)
//                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                selectedToDate = cal.timeInMillis
//                val myFormat = "dd MMM, yyyy"
//                val sdf = SimpleDateFormat(myFormat, Locale.US)
//                textView.text = sdf.format(cal.time)
//            },
//            Calendar.getInstance().get(Calendar.YEAR),
//            Calendar.getInstance().get(Calendar.MONTH),
//            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        )
//
//        datePickerDialog.datePicker.minDate = cal.timeInMillis - 1000
//        cal.add(Calendar.MONTH,1)
//        maxToDate = cal.timeInMillis
//        datePickerDialog.datePicker.maxDate = maxToDate
//        datePickerDialog.show()
//    }
//
//    private fun getToDate(textView: TextView){
//        val datePickerDialog = DatePickerDialog(
//            this,
//            { _, year, monthOfYear, dayOfMonth ->
//                val cal = Calendar.getInstance()
//                cal.set(Calendar.YEAR, year)
//                cal.set(Calendar.MONTH, monthOfYear)
//                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                val myFormat = "dd MMM, yyyy"
//                val sdf = SimpleDateFormat(myFormat, Locale.US)
//                textView.text = sdf.format(cal.time)
//            },
//            Calendar.getInstance().get(Calendar.YEAR),
//            Calendar.getInstance().get(Calendar.MONTH),
//            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//        )
//        datePickerDialog.datePicker.minDate = this.selectedToDate
//        datePickerDialog.datePicker.maxDate = this.maxToDate
//        datePickerDialog.show()
//    }
//    fun setAdapter(){
//        var seats: GetSeats? = null
//        val showSeats = ArrayList<Int>()
//        getSeatsViewModel.getSeats()
//        getSeatsViewModel.apiCaller.observe(
//            this
//        ){
//                data->
//            if(data != null){
//                seats = data
//                data.forEach {
//                    showSeats.add(it.seatName)
//                    Log.e("MainActiviry", it.toString())
//                }
//            }
//        }
////        val availableSeats = findViewById<Spinner>(R.id.seats)
//        val langAdapter = ArrayAdapter<Int>(this,R.layout.spinner_text, showSeats)
//        langAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
//        availableSeats?.adapter = langAdapter
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation = LinearLayoutManager.VERTICAL
////        availableSeats.layoutManager = layoutManager
////        availableSeats.adapter = seats?.let {
////            SeatsAdapter(
////                this,
////                it
////            )
////        }
//    }

    fun authenticate(){
        val loginCred = AuthenticationDO("admin","admin")
        val authenticate by viewModels<LoginViewModel>()
        authenticate.login(loginCred)
        authenticate.apiCaller.observe(
            this
        ){
            data->
            if(data is AuthenticationResponse){
                AppPreferences(this).token  = "Bearer "+data.id_token
            }
        }
    }


}