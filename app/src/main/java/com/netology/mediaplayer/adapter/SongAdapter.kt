package com.netology.mediaplayer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.netology.albumplayer.R
import com.netology.albumplayer.databinding.CardSongBinding
import com.netology.mediaplayer.dto.Song

interface OnInteractionListener {
    fun onPlayOrPauseListener(song: Song)
}
class SongAdapter(private val listener : OnInteractionListener) : ListAdapter<Song, SongAdapter.SongViewHolder>(
    ItemDiffCallback()
) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = CardSongBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SongViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
    class SongViewHolder(val binding: CardSongBinding, private val listener: OnInteractionListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(song: Song) {
            binding.apply {
                title.text = song.title
                if (song.isPlaying==false) playOrPause.setImageResource(R.drawable.play_48)
                else playOrPause.setImageResource(R.drawable.pause_48)

                playOrPause.setOnClickListener {
                    listener.onPlayOrPauseListener(song)
                }
            }
        }
    }
}

class ItemDiffCallback: DiffUtil.ItemCallback<Song>() {

//    Нужен? Вероятно, позволит менять название ...
    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem==newItem
    }

}