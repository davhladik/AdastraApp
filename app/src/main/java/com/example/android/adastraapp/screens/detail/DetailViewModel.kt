package com.example.android.adastraapp.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.android.adastraapp.database.RocketDatabaseDao

class DetailViewModel(val database: RocketDatabaseDao, application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext


}