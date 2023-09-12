package com.netology.mediaplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.netology.mediaplayer.adapter.OnInteractionListener
import com.netology.mediaplayer.adapter.SongAdapter
import com.netology.albumplayer.databinding.ActivityMainBinding
import com.netology.mediaplayer.dto.Song
import com.netology.mediaplayer.viewModels.SongViewModel

class MainActivity : AppCompatActivity() {

//    private val songs = listOf(
//        Song(1, R.raw.ej01, "song1"),
//        Song(2, R.raw.track02, "song2")
//    )
//    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = SongViewModel()
//        val viewModel by viewModels<SongViewModel>()
        val adapter = SongAdapter (object : OnInteractionListener {
            override fun onPlayOrPauseListener(song: Song) {
                if (!song.isPlaying) {

                    viewModel.play(this@MainActivity, song)
                    println(song)
                } else {
                    viewModel.pause(this@MainActivity,song,)
                }
            }
        }
        )

        binding.list.adapter=adapter

//        viewModel.data.observe(this) {
//                adapter.submitList(it.songs)
//            if (it.error) {
//                Snackbar.make(binding.root, "ошибка", Snackbar.LENGTH_LONG)
//                    .setAction(android.R.string.ok) {
//                    }.show()
//            }
//        }

        viewModel.data.observe(this) {
            it.map { song ->
                adapter.submitList(it)
            }
        }


    }
}

//"https://www.soundhelix.com/examples/mp3/Soundhelixmp3-Song-1.mp3"


// первый рабочий вариант
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        with(binding) {
//            playOrPause.setOnClickListener {
//                MediaPlayer.create(getApplicationContext(), R.raw.ej01).start()
////                mediaPlayer.apply {
////                    start()
////                    setOnCompletionListener {
////                        it.release()
////                    }
////                }
//            }
//        }
//    }
//}



//второй работающий вариант
//class MainActivity : AppCompatActivity() {
//
//    private val songs = listOf(
//        Song(1, R.raw.ej01, "song1"),
//        Song(2, R.raw.track02, "song1")
//    )
//
//    private val observer = MediaLifecycleObserver()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        lifecycle.addObserver(observer)
//        with(binding) {
//
//            playOrPause.setOnClickListener {
//                if (observer.mediaPlayer?.isPlaying==false) {
//                    observer.apply {
//                        playOrPause.setImageResource(R.drawable.pause_48)
//                        resources.openRawResourceFd(R.raw.ej01).use { afd ->
//                            mediaPlayer?.setDataSource(
//                                afd.fileDescriptor,
//                                afd.startOffset,
//                                afd.length
//                            )
//                        }
//                    }.play()
//                }
//
//                if (observer.mediaPlayer?.isPlaying==true){
//                    playOrPause.setImageResource(R.drawable.play_48)
////                    NO work
////                    observer.pause()
//                    observer.mediaPlayer?.pause()
//                }
//            }
//
//        }
//    }
//}