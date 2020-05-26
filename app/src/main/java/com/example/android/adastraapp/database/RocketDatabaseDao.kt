package com.example.android.adastraapp.database

import androidx.room.*

@Dao
interface RocketDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(boost: Boosters)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(boosts: List<Boosters>)

    @Update
    fun update(boost: Boosters)

    @Query("SELECT * from booster_item WHERE id = :key")
    fun get(key:String): Boosters?


    @Query("SELECT * FROM booster_item ORDER BY id DESC")
    fun getAllBoosters(): List<Boosters>

    @Query("DELETE FROM booster_item")
    fun clear()
}