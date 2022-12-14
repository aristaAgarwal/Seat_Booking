package com.dishIT.seatbooking.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.seatbooking.R
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.dishIT.seatbooking.constants.AppPreferences
import com.example.seatbooking.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(AppPreferences(this).firstLaunch){
            startActivity(Intent(this, LoginActivity::class.java))
            AppPreferences(this).firstLaunch = false
        }
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
        binding.bookLunch.setOnClickListener {
            startActivity(Intent(this, BookLunchActivity::class.java))
        }
        binding.reserveMeetingRoom.setOnClickListener {
            startActivity(Intent(this, MeetingRoomActivity::class.java))
        }


    }

}