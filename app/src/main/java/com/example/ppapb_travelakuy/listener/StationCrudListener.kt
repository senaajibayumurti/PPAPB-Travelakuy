package com.example.ppapb_travelakuy.listener

import androidx.lifecycle.LiveData
import com.example.ppapb_travelakuy.db.model.TravelForHome

interface StationCrudListener {
    fun addTravel(stasiunAwal: String, stasiunAkhir: String, harga: String)
    fun updateTravel(id: String, stasiunAwal: String, stasiunAkhir: String, harga: String)
    fun deleteTravel(id: String)

    fun getTravel()
    fun getAllTravel(): LiveData<List<TravelForHome>>
}