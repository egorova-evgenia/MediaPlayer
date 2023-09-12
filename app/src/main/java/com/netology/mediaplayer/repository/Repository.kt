package com.netology.mediaplayer.repository

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import com.netology.mediaplayer.dto.Song

interface Repository {
    fun getAll(): LiveData<List<Song>>
    fun toPlay(m: MediaPlayer, song: Song, context: Context)
    fun toPause(m: MediaPlayer, song: Song, context: Context)
    fun getAllAsync(callback: GetAllCallback)
    interface GetAllCallback {
        fun onSuccess(songs: List<Song>){}
        fun onError(e: Exception, code: Int, errorBody: String){}
    }
}