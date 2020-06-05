package com.mdadil2019.videoplayer.adapter

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mdadil2019.videoplayer.MediaRepository
import com.mdadil2019.videoplayer.R
import com.mdadil2019.videoplayer.VideoPlayer
import com.mdadil2019.videoplayer.databinding.VideoItemBinding
import com.mdadil2019.videoplayer.repo.model.Media

class VideoPlayerAdapter(val mediaContents : List<Media>, val videoPlayer: VideoPlayer, val repository: MediaRepository) : RecyclerView.Adapter<VideoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoItemViewHolder {
        val binding =  DataBindingUtil.inflate<VideoItemBinding>(LayoutInflater.from(parent.context),
            R.layout.video_item,parent,false)
        return VideoItemViewHolder(binding, repository)
    }

    override fun getItemCount(): Int {
        return mediaContents.size

    }

    override fun onBindViewHolder(holder: VideoItemViewHolder, position: Int) {
        holder.populateData(mediaContents[position])
    }

    override fun onViewDetachedFromWindow(holder: VideoItemViewHolder) {
        super.onViewDetachedFromWindow(holder)
        videoPlayer.releaseVideo()

    }

    override fun onViewAttachedToWindow(holder: VideoItemViewHolder) {
        super.onViewAttachedToWindow(holder)
                Handler().postDelayed({
                    videoPlayer.playVideo(holder.binding.exoPlayerView,mediaContents[holder.adapterPosition].mediaUrl)
                },500)





    }




}