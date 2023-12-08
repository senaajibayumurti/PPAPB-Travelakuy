package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppapb_travelakuy.databinding.UsermenuHistoryFragmentBinding
import com.example.ppapb_travelakuy.usermenu.database.TravelForHistory
import com.example.ppapb_travelakuy.usermenu.database.TravelForHome

class HistoryFragment : AppCompatActivity() {
    private lateinit var binding: UsermenuHistoryFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val adapterItemTravel = ItemTravelForHistoryAdapter(listOfTravel())

        super.onCreate(savedInstanceState)

        binding = UsermenuHistoryFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            rvListTravel.apply {
                adapter = adapterItemTravel
                layoutManager = LinearLayoutManager(this@HistoryFragment)
            }
        }
    }
    private fun listOfTravel(): List<TravelForHistory> {
        return listOf()
    }
}