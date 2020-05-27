package com.example.android.adastraapp.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.adastraapp.database.Boosters
import com.example.android.adastraapp.database.RocketDatabaseDao
import com.example.android.adastraapp.screens.*
import kotlinx.coroutines.*

class DetailViewModel(val database: RocketDatabaseDao,boosterId: String="", application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext


    private val _navigateToOverview = MutableLiveData<Boolean>()
    val navigateToOverview
        get() = _navigateToOverview

    private val _thisBooster = MutableLiveData<Boosters>()
    val thisBooster
        get() = _thisBooster

    /**
     * Values for displaying
     */
    val selectedSerial = Transformations.map(thisBooster){
        detailBoosterSerial(it)
    }
    val selectedStatus = Transformations.map(thisBooster){
        detailBoosterStatus(it)
    }
    val selectedBlock = Transformations.map(thisBooster){
        detailBoosterBlock(it)
    }
    val selectedLaunchDate = Transformations.map(thisBooster){
        detailBoosterLaunchDate(it)
    }
    val selectedReuseCount = Transformations.map(thisBooster){
        detailBoosterReuseCount(it)
    }

    val selectedDetails = Transformations.map(thisBooster){
        detailBoosterDetails(it)
    }



    /**
     * Coroutines
     */
    private var viewModelJob = Job()
    private val coroutineScope= CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        initializeThis(boosterId)
    }

    /**
     * Database functions
     */

    private fun initializeThis(id:String) {
        coroutineScope.launch {
            thisBooster.value = getFromDatabase(id)

        }
    }

    private suspend fun getFromDatabase(id:String):Boosters?{
        return withContext(Dispatchers.IO) {
            var booster = database.get(id)

            booster
        }
    }



    /**
     * Navigation function
     */

    fun onArrowBackClicked(){
        _navigateToOverview.value = true
    }

    fun doneArrowBackClicked(){
        _navigateToOverview.value=false
    }

    /**
     * Canceling coroutines
     */

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}