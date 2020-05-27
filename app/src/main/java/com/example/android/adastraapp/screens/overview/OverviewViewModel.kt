package com.example.android.adastraapp.screens.overview

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.adastraapp.database.Boosters
import com.example.android.adastraapp.database.RocketDatabaseDao
import com.example.android.adastraapp.network.SpaceApi
import kotlinx.coroutines.*
import java.lang.Exception


class OverviewViewModel(val database: RocketDatabaseDao, application: Application) : AndroidViewModel(application){

    private val context = getApplication<Application>().applicationContext


    private val _flag = MutableLiveData<Boolean>()
    val flag : LiveData<Boolean>
        get() = _flag

    private val _navigateToItemDetail = MutableLiveData<String>()
    val navigateToItemDetail
        get() = _navigateToItemDetail

    /**
     * Variable for showing the list of data in recyclerview
     */
    val listOfBoosters = MutableLiveData<List<Boosters>>()


    /**
     * Coroutines
     */
    private var viewModelJob = Job()
    private val coroutineScope= CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        getBoosterProperties()
        //After start it will check is the database is empty, if not -> it will load the data from database
        initializeThis()
    }

    private fun getBoosterProperties() {
        coroutineScope.launch {

            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = SpaceApi.retrofitService.getProperties()
            try {
                // this will run on a thread managed by Retrofit
                val listResult = getPropertiesDeferred.await()
                insertAll(listResult)
                _flag.value=true

            } catch (e: Exception) {
                Log.i("chci","error")
            }
        }
    }


    /**
     * Database functions
     */

    fun initializeThis() {
        coroutineScope.launch {
            listOfBoosters.value = getAllBoosters()
        }
    }

    private suspend fun insertAll(boosters: List<Boosters>) {
        withContext(Dispatchers.IO) {
            database.insertAll(boosters)
        }
    }

    private suspend fun getAllBoosters(): List<Boosters>? {
        return withContext(Dispatchers.IO) {
            var allBoosters = database.getAllBoosters()
            val dbReadyCheck = allBoosters.isEmpty()

            if (dbReadyCheck){
                allBoosters = emptyList()
            }
            allBoosters
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }


    /**
     * Navigation functions
     */


    fun onItemClicked(id:String){
        _navigateToItemDetail.value=id
    }


    fun doneNavigatingToItemDetail(){
        _navigateToItemDetail.value=null
    }

    /**
     * Function for implementing delete button
     */

    fun onClear() {
        coroutineScope.launch {
            // Clear the database table.
            clear()
            Toast.makeText(context, "All data deleted!", Toast.LENGTH_SHORT).show()
        }
    }

    fun wantToDeleteAll(){
        onClear()
    }

    /**
     * Canceling coroutines
     */

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}