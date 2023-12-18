package com.example.ppapb_travelakuy.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "travel")
data class TravelRoom (
    @PrimaryKey(autoGenerate = true)
    @NotNull
    var id: Int = 0,
    val stasiunOne: String,
    val stasiunTwo: String,
    val harga: String,
)