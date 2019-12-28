package com.example.projectandroid.data.network

import com.example.projectandroid.model.Game
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

private const val BASE_URL_GAMES = "https://steamspy.com/api.php?request="

private val moshi = Moshi.Builder().
    add(KotlinJsonAdapterFactory())
    .add(NULL_TO_EMPTY_STRING_ADAPTER)
    .add(DateAdapter)
    .build()

/*interface GameApiService{
    @GET("top100forever")
    suspend fun getGames(): List<Game>
}*/



object Network {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GAMES)
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .client(unSafeOkHttpClient().build())
        .build()

    val game = retrofit.create(GameApiService::class.java)

}


object NULL_TO_EMPTY_STRING_ADAPTER {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextString()
        }
        reader.nextNull<Unit>()
        return ""
    }
}
object DateAdapter{
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    @FromJson
    fun fromJson(reader: JsonReader): Date? {
        return try {
            val dateAsString = reader.nextString()
            dateFormat.parse(dateAsString)
        } catch (e: Exception) {
            null
        }
    }
    @ToJson
    fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(dateFormat.format(value))
        }
    }
}


fun unSafeOkHttpClient() : OkHttpClient.Builder {
    val okHttpClient = OkHttpClient.Builder()
    try {
        // Create a trust manager that does not validate certificate chains
        val trustAllCerts: Array<TrustManager> = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<out X509Certificate>?,
                authType: String?
            ) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory
        if (trustAllCerts.isNotEmpty() && trustAllCerts.first() is X509TrustManager) {
            okHttpClient.sslSocketFactory(
                sslSocketFactory,
                trustAllCerts.first() as X509TrustManager
            )
            okHttpClient.hostnameVerifier { _, _ -> true }
        }
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.connectTimeout(1000, TimeUnit.SECONDS)
        okHttpClient.readTimeout(1000, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(1000, TimeUnit.SECONDS)
        return okHttpClient
    } catch (e: Exception) {
        return okHttpClient
    }
}
