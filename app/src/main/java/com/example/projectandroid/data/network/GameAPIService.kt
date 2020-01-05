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
import retrofit2.http.Query

private const val BASE_URL_GAMES = "https://steamspy.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// OkhttpClient for building http request url
private val tmdbClient = OkHttpClient().newBuilder()
    .build()

private val retrofit = Retrofit.Builder()
    .client(tmdbClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL_GAMES)
    .build()

interface GameApiService {
    @GET("api.php")
    fun getTop100(@Query("request") request: String): Deferred<Map<String, Game>>
}

object GameApi {
    val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
}
