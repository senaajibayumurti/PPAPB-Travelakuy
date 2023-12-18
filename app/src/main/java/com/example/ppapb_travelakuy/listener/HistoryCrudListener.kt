package com.example.ppapb_travelakuy.listener

import androidx.lifecycle.LiveData
import com.example.ppapb_travelakuy.db.model.TravelForHistory

interface HistoryCrudListener {
    fun deleteHistory(travelForHistory: TravelForHistory)
    fun insertHistory(travelForHistory: TravelForHistory)
    fun getAllHistory(id: String): LiveData<List<TravelForHistory>>
}