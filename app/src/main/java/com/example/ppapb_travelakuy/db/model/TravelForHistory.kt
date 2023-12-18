package com.example.ppapb_travelakuy.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "history")
data class TravelForHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userID: String = "",
    val station_one: String = "",
    val station_two: String = "",
    val schedule_date: String = "",
    val schedule_time: String = "",
    val price: Int = 0
)
