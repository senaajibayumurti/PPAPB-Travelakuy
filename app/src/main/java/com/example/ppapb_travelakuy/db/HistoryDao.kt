package com.example.ppapb_travelakuy.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.ppapb_travelakuy.db.model.TravelForHistory

@Dao
interface HistoryDao {
    @Delete
    fun deleteHistory(travelForHistory: TravelForHistory)
    @Insert(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    fun insertHistory(travelForHistory: TravelForHistory)
    @Query("SELECT * FROM history WHERE userID = :id ORDER BY schedule_date")
    fun getAllHistory(id: String): LiveData<List<TravelForHistory>>
}