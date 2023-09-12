package com.netology.mediaplayer.viewModels

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.netology.mediaplayer.dto.Song
import com.netology.mediaplayer.repository.PostRepositoryInMemory
import com.netology.mediaplayer.repository.Repository

class SongViewModel : ViewModel() {

//    из памяти
    private val repository: Repository = PostRepositoryInMemory()
    private var mediaPlayer=MediaPlayer()

    val data = repository.getAll()

    fun play(context: Context, song: Song, ) = repository.toPlay(mediaPlayer,song,context)
    fun pause(context: Context, song: Song,) =repository.toPause(mediaPlayer,song,context)

    fun playList(context: Context, firstId: Int, songList: List<Song>){
        play(context,songList[firstId])
        var currentId = firstId
        while (true) {
        mediaPlayer.setOnCompletionListener {
            it.release()
            currentId=currentId++
            play(context, songList[currentId++])
        }
    }

    }
}


//private val repository: Repository = RepositoryImp()
//private val _data = MutableLiveData(FeedModel())
//val data: LiveData<FeedModel>
//    get() = _data

//fun loadAlbum() {
////        _data.value = FeedModel(loading = true)
//    repository.getAllAsync(object : Repository.GetAllCallback {
//        override fun onSuccess(songs: List<Song>) {
//            _data.postValue(FeedModel(songs = songs, empty = songs.isEmpty()))
//        }
//        override fun onError(e: Exception, code: Int, errorBody: String) {
//            _data.postValue(FeedModel(error = true))
//        }
//    } )
//}
//
//init {
//    loadAlbum()
//}
