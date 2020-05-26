package com.example.android.adastraapp.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.adastraapp.database.RocketDatabaseDao

class DetailViewModel(val database: RocketDatabaseDao, application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext


    private val _navigateToOverview = MutableLiveData<Boolean>()
    val navigateToOverview
        get() = _navigateToOverview


    /**
     * Navigation function
     */

    fun onArrowBackClicked(){
        _navigateToOverview.value = true
    }

    fun doneArrowBackClicked(){
        _navigateToOverview.value=false
    }
}