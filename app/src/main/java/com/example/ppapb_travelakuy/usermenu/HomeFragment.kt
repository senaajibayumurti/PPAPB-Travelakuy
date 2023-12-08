package com.example.ppapb_travelakuy.usermenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ppapb_travelakuy.databinding.UsermenuHomeFragmentBinding
import com.example.ppapb_travelakuy.usermenu.database.TravelForHome

class HomeFragment : AppCompatActivity() {
    private lateinit var binding: UsermenuHomeFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val adapterItemTravel = ItemTravelForHomeAdapter(listOfTravel())

        super.onCreate(savedInstanceState)
        binding = UsermenuHomeFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            rvListTravel.apply {
                adapter = adapterItemTravel
                layoutManager = LinearLayoutManager(this@HomeFragment)
            }
        }
    }
    private fun listOfTravel(): List<TravelForHome> {
        return listOf()
    }
}