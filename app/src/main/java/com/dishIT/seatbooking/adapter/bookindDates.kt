package com.dishIT.seatbooking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.seatbooking.R

class bookindDates(
    var context: Context,
    var dates : List<String>
):
    RecyclerView.Adapter<bookindDates.RACItemHolder>()  {

    inner class RACItemHolder(v: View): RecyclerView.ViewHolder(v) {
        private var view: View = v
        val date = view.findViewById<TextView>(R.id.date)
        fun bindItem(list: String){
            date.text = list
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): bookindDates.RACItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(
        R.layout.booking_dates_row,
        parent, false)
        this.context = parent.context
        return RACItemHolder(view)
    }

    override fun onBindViewHolder(holder: bookindDates.RACItemHolder, position: Int) {
        holder.bindItem(dates[position])
    }

    override fun getItemCount() = dates.size
}