package com.example.ppapb_travelakuy.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ppapb_travelakuy.db.model.TravelForHistory


@Database(entities = [TravelForHistory::class], version = 1, exportSchema = false)
abstract class HistoryDB : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var instance: HistoryDB? = null
        fun getDatabase(context: Context): HistoryDB? {
            synchronized(HistoryDB::class.java) {
                if (instance == null) {
                    instance = androidx.room.Room.databaseBuilder(context.applicationContext,
                        HistoryDB::class.java, "history_database")
                        .build()
                }
                return instance
            }
        }
    }
}
