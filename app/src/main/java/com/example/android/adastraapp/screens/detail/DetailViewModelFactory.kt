package com.example.android.adastraapp.screens.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.adastraapp.database.RocketDatabaseDao

class DetailViewModelFactory(
    private val dataSource: RocketDatabaseDao,
    private val boosterId: String,
    private val application: Application
) : ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dataSource,boosterId,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}