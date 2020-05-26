package com.example.android.adastraapp.network

import com.example.android.adastraapp.database.Boosters
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://api.spacexdata.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface SpaceApiService{
    @GET("v3/cores")
    fun getProperties():
            Deferred<List<Boosters>>

}

//interface SpecificRecipeApiService{
//    @GET("api/v1/recipes/{id}")
//    fun getProperty(@Path("id") id:String):
//            Deferred<SpecificRecipeNetwork>
//    @POST("api/v1/recipes")
//    fun uploadProperties(@Body newRecipe: SpecificRecipeUpload):
//            Deferred<SpecificRecipeNetwork>
//
//}

object SpaceApi {
    val retrofitService: SpaceApiService by lazy {
        retrofit.create(SpaceApiService::class.java)
    }
}