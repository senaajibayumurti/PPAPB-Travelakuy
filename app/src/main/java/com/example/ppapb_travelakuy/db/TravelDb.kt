package com.example.ppapb_travelakuy.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ppapb_travelakuy.db.model.TravelRoom


@Database(entities = [TravelRoom::class], version = 1, exportSchema = false)
abstract class TravelDb: RoomDatabase() {
    abstract fun travelDao(): TravelDao

    companion object {
        @Volatile
        private var instance: TravelDb? = null

        fun getDatabase(context: Context): TravelDb? {
            synchronized(HistoryDB::class.java) {
                if (instance == null) {
                    instance = androidx.room.Room.databaseBuilder(context.applicationContext,
                        TravelDb::class.java, "travel_database")
                        .build()
                }
                return instance
            }
        }
    }
}
