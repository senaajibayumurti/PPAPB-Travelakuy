package com.example.ppapb_travelakuy.usermenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_travelakuy.databinding.UsermenuItemTravelHomeBinding
import com.example.ppapb_travelakuy.usermenu.database.TravelForHome

class ItemTravelForHomeAdapter (private val listTravelForHome:List<TravelForHome>):
        RecyclerView.Adapter<ItemTravelForHomeAdapter.ItemTravelViewHolder>(){
            inner class ItemTravelViewHolder(private val binding: UsermenuItemTravelHomeBinding):
                    RecyclerView.ViewHolder(binding.root){
                        fun bind(data: TravelForHome){
                            with(binding){
                                tvStationOne.text = data.station_one
                                tvStationTwo.text = data.station_two
                                tvPrice.text = data.price.toString()

                                ivTravelIcon.setOnClickListener {
                                    TODO("")
                                }
                            }
                        }
                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTravelViewHolder {
        val binding = UsermenuItemTravelHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTravelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTravelForHome.size
    }

    override fun onBindViewHolder(holder: ItemTravelViewHolder, position: Int) {
        holder.bind(listTravelForHome[position])
    }
}