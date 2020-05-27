package com.example.android.adastraapp.network

import com.example.android.adastraapp.database.Boosters
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api.spacexdata.com/v3/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()


interface SpaceApiService{
    @GET("cores")
    fun getProperties():
            Deferred<List<Boosters>>


}



object SpaceApi {
    val retrofitService: SpaceApiService by lazy {
        retrofit.create(SpaceApiService::class.java)
    }
}

