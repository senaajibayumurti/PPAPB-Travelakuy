package com.example.ppapb_travelakuy.usermenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_travelakuy.databinding.UsermenuItemTravelBinding
import com.example.ppapb_travelakuy.usermenu.database.Travel

class ItemTravelAdapter (private val listTravel:List<Travel>):
        RecyclerView.Adapter<ItemTravelAdapter.ItemTravelViewHolder>(){
            inner class ItemTravelViewHolder(private val binding: UsermenuItemTravelBinding):
                    RecyclerView.ViewHolder(binding.root){
                        fun bind(data: Travel){
                            with(binding){
                                tvStationOne.text = data.station_one
                                tvStationTwo.text = data.station_two
                                tvPrice.text = data.price.toString()

                                ivTravelIcon.setOnClickListener {
                                    TODO("pindahin ke riawayat???")
                                }
                            }
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTravelViewHolder {
        val binding = UsermenuItemTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTravelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTravel.size
    }

    override fun onBindViewHolder(holder: ItemTravelViewHolder, position: Int) {
        holder.bind(listTravel[position])
    }
}