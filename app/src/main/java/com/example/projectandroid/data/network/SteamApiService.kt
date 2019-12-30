package com.example.projectandroid.data.network

import com.example.projectandroid.model.Game
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL_STEAM = "https://store.steampowered.com/api/"

private val moshi = Moshi.Builder()
    //.add(ResponseGetGamesAdapter())
    .add(KotlinJsonAdapterFactory())
    .build()

//OkhttpClient for building http request url
private val tmdbClient = OkHttpClient().newBuilder()
    .build()

private val retrofit = Retrofit.Builder()
    .client(tmdbClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL_STEAM)
    .build()


interface SteamApiService{
    @GET("appdetails?appids=218620")
    fun getTop100(): Deferred<Map<String, Game>>

}



object SteamApi {
    val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
}