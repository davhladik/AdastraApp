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

class OverviewViewModel(val database: RocketDatabaseDao, application: Application) : AndroidViewModel(application){

    private val context = getApplication<Application>().applicationContext



    private val _flag = MutableLiveData<Boolean>()
    val flag : LiveData<Boolean>
        get() = _flag

    private val _navigateToItemDetail = MutableLiveData<String>()
    val navigateToItemDetail
        get() = _navigateToItemDetail

    private val _properties = MutableLiveData<List<Boosters>>()
    val properties : LiveData<List<Boosters>>
        get() = _properties


    private val _dbCheck = MutableLiveData<Boolean>()
    val dbCheck : LiveData<Boolean>
        get() = _dbCheck



    val listOfRecipes = MutableLiveData<List<Boosters>>()






    /**
     * Coroutines
     */
    private var viewModelJob = Job()
    private val coroutineScope= CoroutineScope(Dispatchers.Main + viewModelJob)


    init {
        getRecipeProperties()

//        initializeThis()

    }

    private fun getRecipeProperties() {
        coroutineScope.launch {

            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = SpaceApi.retrofitService.getProperties()
            try {
                // this will run on a thread managed by Retrofit
                Log.i("chci","download")
                val listResult = getPropertiesDeferred.getCompleted()

                Log.i("chci",listResult.size.toString())
//                insertAll(listResult)
//                _flag.value=true

                _properties.value = listResult
            } catch (e: Exception) {
                Log.i("chci","error")
                _properties.value = ArrayList()
            }
        }
    }

//    fun isDatabaseEmpty():Boolean{
//        var dbReadyCheck:Boolean
//        coroutineScope.launch {
//            withContext(Dispatchers.IO){
//                dbReadyCheck = database.getAllRecipes().isNotEmpty()
//
//            }
//
//        }
//        return dbReadyCheck
//    }



    /**
     * Database functions
     */

    fun initializeThis() {
        coroutineScope.launch {
            listOfRecipes.value = getAllBoosters()
        }
    }

    private suspend fun insertAll(recipe: List<Boosters>) {
        withContext(Dispatchers.IO) {
            database.insertAll(recipe)
        }
    }

    private suspend fun getAllBoosters(): List<Boosters>? {
        return withContext(Dispatchers.IO) {
            var allRecipes = database.getAllBoosters()
//            val dbReadyCheck = allRecipes.isEmpty()
//            Log.i("chci",allRecipes.size.toString())
//            Log.i("chci",dbReadyCheck.toString())
//            if (dbReadyCheck){
//                allRecipes = emptyList()
//            }
            allRecipes
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

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

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


}