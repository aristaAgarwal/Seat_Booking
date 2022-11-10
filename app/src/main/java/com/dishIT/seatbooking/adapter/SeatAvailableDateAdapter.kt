package com.dishIT.seatbooking.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seatbooking.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SeatAvailableDateAdapter(
    private var context: Context,
    availableDate: List<String>,
    var appLinkListener: AppLinkClick
): RecyclerView.Adapter<SeatAvailableDateAdapter.RACItemHolder>()
{

    private var list = availableDate
    inner class RACItemHolder(v: View): RecyclerView.ViewHolder(v){

        private var view: View = v
        val date  = view.findViewById<TextView>(R.id.date)
        val checkbox = view.findViewById<CheckBox>(R.id.checkbox)
        val day = view.findViewById<TextView>(R.id.day)
        fun bindItem(list: String){
            date.text = list
            day.text = getDay(list)
            checkbox.setOnCheckedChangeListener { compoundButton, b ->
                appLinkListener.onAppLinkClicked(b, list)
            }
        }

    }
    @SuppressLint("SimpleDateFormat")
    fun getDay(date:  String): String{
        val date1 = SimpleDateFormat("yyyy-MM-dd").parse(date)
        val sdf = SimpleDateFormat("EEEE")
        return sdf.format(date1!!)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RACItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.available_seat_single_item,
        parent, false)
        this.context = parent.context
        return RACItemHolder(view)
    }

    override fun onBindViewHolder(holder: RACItemHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount() = list.size

    interface AppLinkClick {
        fun onAppLinkClicked(isChecked: Boolean, date: String)
    }
}