package com.netology.mediaplayer.repository

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.netology.albumplayer.R
import com.netology.mediaplayer.dto.Song

class PostRepositoryInMemory : Repository {

    private var songs = listOf(
        Song(1, R.raw.ej01, "song1"),
        Song(2, R.raw.track02, "song1")
    )

    private val data = MutableLiveData(songs)
    override fun getAll(): LiveData<List<Song>> = data

    override fun toPlay(m: MediaPlayer, song: Song, context: Context) {
        context.getResources().openRawResourceFd(song.resId).use {
            m.setDataSource(
                it.fileDescriptor,
                it.startOffset,
                it.length) }
        m.setOnPreparedListener {it.start()}
        songs = songs.map {
            if (it.id==song.id) {it.copy(isPlaying = true)} else it
        }
    }

    override fun toPause(m: MediaPlayer, song: Song, context: Context) {
        m.pause()
        songs = songs.map {
            it.copy(isPlaying = false)
        }
    }

    override fun getAllAsync(callback: Repository.GetAllCallback) {
        TODO("Not yet implemented")
    }
}