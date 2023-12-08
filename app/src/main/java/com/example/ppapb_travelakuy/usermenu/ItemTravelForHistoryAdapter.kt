package com.example.ppapb_travelakuy.usermenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_travelakuy.databinding.UsermenuItemTravelHistoryBinding
import com.example.ppapb_travelakuy.usermenu.database.TravelForHistory

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
                                tvPrice.text = data.price.toString()
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
}