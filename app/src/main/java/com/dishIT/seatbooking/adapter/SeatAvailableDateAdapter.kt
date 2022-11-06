package com.dishIT.seatbooking.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dishIT.seatbooking.model.AvailableDatesResponseItem
import com.example.seatbooking.R

class SeatAvailableDateAdapter(
    private var context: Context,
    availableDate: ArrayList<AvailableDatesResponseItem>
): RecyclerView.Adapter<SeatAvailableDateAdapter.RACItemHolder>()
{

    private var list = availableDate
    inner class RACItemHolder(v: View): RecyclerView.ViewHolder(v){

        private var view: View = v
        val date  = view.findViewById<TextView>(R.id.date)
        fun bindItem(list: AvailableDatesResponseItem){
            date.text = list.date
        }
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
}