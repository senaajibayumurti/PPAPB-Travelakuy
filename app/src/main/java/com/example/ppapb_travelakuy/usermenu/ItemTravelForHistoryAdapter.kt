package com.example.ppapb_travelakuy.usermenu

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_travelakuy.databinding.UsermenuItemTravelHistoryBinding
import com.example.ppapb_travelakuy.db.model.TravelForHistory
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ItemTravelForHistoryAdapter (private val listTravelForHistory:List<TravelForHistory>):
        RecyclerView.Adapter<ItemTravelForHistoryAdapter.ItemTravelViewHolder>(){
            inner class ItemTravelViewHolder(private val binding: UsermenuItemTravelHistoryBinding):
                    RecyclerView.ViewHolder(binding.root){
                        fun bind(data: TravelForHistory){
                            with(binding){
                                tvStationOne.text = data.station_one
                                tvStationTwo.text = data.station_two
                                tvDate.text = data.schedule_date
                                tvTime.text = data.schedule_time
                                tvPrice.text = formatToRupiah(data.price)

                                if(hasDateTimePassed(data.schedule_date, data.schedule_time)) {
                                    selesai.visibility = View.GONE
                                } else {
                                    selesai.visibility = View.VISIBLE
                                }
                            }
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTravelViewHolder {
        val binding = UsermenuItemTravelHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTravelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTravelForHistory.size
    }

    override fun onBindViewHolder(holder: ItemTravelViewHolder, position: Int) {
        holder.bind(listTravelForHistory[position])
    }
    private fun formatToRupiah(number: Int): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number.toLong()).toString()
    }

    fun hasDateTimePassed(date: String, time: String): Boolean {
        val currentDateTime = Calendar.getInstance().time
        val dateTimeFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())

        try {
            val combinedDateTime = "$date $time"
            val inputDateTime = dateTimeFormat.parse(combinedDateTime)
            Log.d("ItemTravelForHistoryAdapter", "Input date and time: ${inputDateTime.time}")
            Log.d("ItemTravelForHistoryAdapter", "Current date and time: ${currentDateTime.time}")

            return inputDateTime != null && inputDateTime.after(currentDateTime)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }
}