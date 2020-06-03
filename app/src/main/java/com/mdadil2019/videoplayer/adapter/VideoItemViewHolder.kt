package com.mdadil2019.videoplayer.adapter

import androidx.recyclerview.widget.RecyclerView
import com.mdadil2019.videoplayer.databinding.VideoItemBinding
import com.mdadil2019.videoplayer.model.Media

class VideoItemViewHolder(val binding : VideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun populateData(media : Media){
        binding.media = media
    }
}