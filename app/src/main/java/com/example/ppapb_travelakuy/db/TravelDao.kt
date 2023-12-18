package com.example.ppapb_travelakuy.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ppapb_travelakuy.db.model.TravelRoom

@Dao
interface TravelDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTravel(travelRoom: TravelRoom)

    @Delete
    fun deleteTravel(travelRoom: TravelRoom)

    @Query("SELECT * FROM travel")
    fun getAllTravel(): LiveData<List<TravelRoom>>

    @Query("DELETE FROM travel")
    fun deleteAllTravel()

}