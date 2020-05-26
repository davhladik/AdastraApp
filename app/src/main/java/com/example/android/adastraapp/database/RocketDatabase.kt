package com.example.android.adastraapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Boosters::class],version = 1,exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class RocketDatabase : RoomDatabase() {

    abstract val rocketDatabaseDao: RocketDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE: RocketDatabase? = null

        fun getInstance(context: Context) : RocketDatabase {
            synchronized(this){

                var instance= INSTANCE

                if (instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RocketDatabase::class.java,
                        "specific_recipe_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE =instance
                }
                return instance
            }}
    }

}