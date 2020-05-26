package com.example.android.adastraapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booster_item")
data class Boosters(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val core_serial: String,
    val status: String,
    val block: Int,
    val original_launch: String,
    val missions: List<String>,
    val reuse_counts: Int,
    val details: String
)