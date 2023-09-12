package com.netology.mediaplayer.dto

data class Song(
    val id: Int,
    val resId: Int,
    val title: String,
    val url: String? = null,
    val isPlaying: Boolean = false
)

