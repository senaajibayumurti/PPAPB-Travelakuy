package com.example.ppapb_travelakuy.usermenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_travelakuy.databinding.UsermenuItemTravelHomeBinding
import com.example.ppapb_travelakuy.db.model.TravelForHome
import com.example.ppapb_travelakuy.db.model.TravelRoom

class ItemTravelForHomeRoomAdapter(private val listTravel: List<TravelRoom>, private val onClick: (TravelRoom) -> Unit): RecyclerView.Adapter<ItemTravelForHomeRoomAdapter.ItemTravelViewHolder>() {

    inner class ItemTravelViewHolder(private val binding: UsermenuItemTravelHomeBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: TravelRoom){
            with(binding){
                tvStationOne.text = data.stasiunOne
                tvStationTwo.text = data.stasiunTwo
                tvPrice.text = data.harga
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTravelViewHolder {
        val binding = UsermenuItemTravelHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemTravelViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTravel.size
    }

    override fun onBindViewHolder(holder: ItemTravelViewHolder, position: Int) {
        holder.bind(listTravel[position])
    }


}