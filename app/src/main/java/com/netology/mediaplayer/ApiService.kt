package com.netology.mediaplayer

import com.netology.mediaplayer.dto.Song
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

//Можно указать полный URL в запросе, тогда базовый URL будет проигнорирован:
private const val BASE_URL = "https://github.com/netology-code/andad-homeworks/raw/master/09_multimedia/data/album.json/"
//file
interface ApiService {
    @GET
    fun getAll(): Call<List<Song>>
}

object Api {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
//        .client(okhttp)
        .build()
    val service: ApiService by lazy{
        retrofit.create()
    }
}

//private val logging = HttpLoggingInterceptor().apply {
//    if (BuildConfig.DEBUG) {
//        level = HttpLoggingInterceptor.Level.BODY
//    }
//}
//
//private val okhttp = OkHttpClient.Builder()
//    .addInterceptor(logging)
//    .build()