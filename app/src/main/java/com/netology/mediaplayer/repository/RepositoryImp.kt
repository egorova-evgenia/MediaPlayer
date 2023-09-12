package com.netology.mediaplayer.repository

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import com.netology.mediaplayer.Api
import com.netology.mediaplayer.dto.Song
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImp : Repository {

    override fun getAllAsync(callback: Repository.GetAllCallback) {
        Api.service.getAll()
            .enqueue(object : Callback<List<Song>> {
                override fun onResponse(call: Call<List<Song>>, response: Response<List<Song>>) {
                    if (!response.isSuccessful) {
                        callback.onError(RuntimeException(response.message()),response.code(),response.errorBody().toString())
                        return
                    }
                    callback.onSuccess(response.body() ?: emptyList())
                }
                override fun onFailure(call: Call<List<Song>>, t: Throwable) {
                    callback.onError(RuntimeException(t),500,"ошибка сервера")
                }
            })
    }

    override fun getAll(): LiveData<List<Song>> {
        TODO("Not yet implemented")
    }

    override fun toPlay(m: MediaPlayer, song: Song, context: Context) {
        TODO("Not yet implemented")
    }

    override fun toPause(m: MediaPlayer, song: Song, context: Context) {
        TODO("Not yet implemented")
    }
}