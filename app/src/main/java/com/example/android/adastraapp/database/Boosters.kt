package com.example.android.adastraapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booster_item")
data class Boosters(
    @PrimaryKey()
    val core_serial: String,
    val status: String,
    val block: Int?,
    val original_launch: String?,
    val original_launch_unix: Double?,
    val reuse_count: Int,
    val rtls_attempts: Int,
    val water_landing: Boolean,
    val details: String?
)
