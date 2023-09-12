package com.netology.mediaplayer.viewModels

import com.netology.mediaplayer.dto.Song

data class FeedModel(
    val songs: List<Song> = emptyList(),
//    val loading: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = false,
)