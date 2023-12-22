package com.example.ppapb_travelakuy.usermenu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ppapb_travelakuy.databinding.UsermenuItemTravelHomeBinding
import com.example.ppapb_travelakuy.db.model.TravelForHome
import java.text.NumberFormat
import java.util.Locale

class ItemTravelForHomeAdapter (private val listTravelForHome:List<TravelForHome>, private val isAdmin: Boolean = false, private val onClick : (TravelForHome) -> Unit = {},
                                private val addHistory: (TravelForHome) -> Unit = {}):
        RecyclerView.Adapter<ItemTravelForHomeAdapter.ItemTravelViewHolder>(){
        inner class ItemTravelViewHolder(private val binding: UsermenuItemTravelHomeBinding):
                RecyclerView.ViewHolder(binding.root){
                    fun bind(data: TravelForHome){
                        with(binding){
                            tvStationOne.text = data.station_one
                            tvStationTwo.text = data.station_two
                            tvPrice.text = formatToRupiah(data.price)
                            if(!isAdmin) {
                                delete.visibility = View.GONE
                                root.setOnClickListener {
                                    addHistory(data)
                                }
                            } else {
                                delete.setOnClickListener {
                                    onClick(data)
                                }
                            }

                        }
                    }
                }

    private fun formatToRupiah(number: Int): String {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number.toLong()).toString()
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