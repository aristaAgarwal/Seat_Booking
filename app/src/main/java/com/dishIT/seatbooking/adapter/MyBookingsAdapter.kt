package com.dishIT.seatbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dishIT.seatbooking.model.MyBookings
import com.dishIT.seatbooking.model.MyBookingsItem
import com.example.seatbooking.R

class MyBookingsAdapter(
    var context: Context,
    val paymentMethods: MyBookings,
    var appLinkListener: AppLinkClick
) :
RecyclerView.Adapter<MyBookingsAdapter.RACItemHolder>() {

    inner class RACItemHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v
        val seat = view.findViewById<TextView>(R.id.seatNo)
        val floor = view.findViewById<TextView>(R.id.floor)
        val date = view.findViewById<TextView>(R.id.date)
        val delete = view.findViewById<ImageView>(R.id.delete)
        fun bindItem(list: MyBookingsItem){
            seat.text = list.seatName.toString()
            date.text = list.occurrenceDate
            floor.text = list.floorName.toString()
            delete.setOnClickListener {
                appLinkListener.onAppLinkClicked(list.empId, list.occurrenceDate)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RACItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.my_bookings_single_item,
            parent, false)
        this.context = parent.context
        return RACItemHolder(view)
    }

    override fun onBindViewHolder(holder: RACItemHolder, position: Int) {
        holder.bindItem(paymentMethods[position])
    }

    override fun getItemCount() = paymentMethods.size

    interface AppLinkClick {
        fun onAppLinkClicked(id: String, date: String)
    }
}