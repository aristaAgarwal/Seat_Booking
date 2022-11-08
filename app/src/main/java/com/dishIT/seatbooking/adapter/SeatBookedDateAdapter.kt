package com.dishIT.seatbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dishIT.seatbooking.model.AvailableDatesResponseItem
import com.example.seatbooking.R

class SeatBookedDateAdapter(
    private var context: Context,
    bookedDate: List<AvailableDatesResponseItem>,
    var appLinkListener: AppLinkClick
): RecyclerView.Adapter<SeatBookedDateAdapter.RACItemHolder>()
{

    private var list = bookedDate
    inner class RACItemHolder(v: View): RecyclerView.ViewHolder(v){

        private var view: View = v
        val date  = view.findViewById<TextView>(R.id.date)
        val name = view.findViewById<TextView>(R.id.username)
        val requestBtn  = view.findViewById<TextView>(R.id.request)
        fun bindItem(list: AvailableDatesResponseItem){
            date.text = list.date
            name.text = list.bookedUser?.empName
            requestBtn.setOnClickListener {
                appLinkListener.onAppLinkClicked(list.bookedUser!!.empId, list.bookedUser.empName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RACItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.booked_seat_single_item,
            parent, false)
        this.context = parent.context
        return RACItemHolder(view)
    }

    override fun onBindViewHolder(holder: RACItemHolder, position: Int) {
        holder.bindItem(list[position])
    }

    override fun getItemCount() = list.size

    interface AppLinkClick {
        fun onAppLinkClicked(id: String, name: String)
    }
}