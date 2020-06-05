package com.mdadil2019.videoplayer.adapter

import android.util.Log
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.mdadil2019.videoplayer.MediaRepository
import com.mdadil2019.videoplayer.databinding.VideoItemBinding
import com.mdadil2019.videoplayer.repo.model.Media
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VideoItemViewHolder(val binding : VideoItemBinding, val repository: MediaRepository) : RecyclerView.ViewHolder(binding.root) {

//    var isCurrentlyBookmarked = ObservableField<Boolean>()

    var bookmarkText = ObservableField<String>("Tap to bookmark")
    var mediaObject : Media? = null


    fun populateData(media : Media){
        binding.viewModel = this

        mediaObject = media
        repository.queryMedia(media.mediaUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mediaObject = it
                binding.media = it
                if(it.isBookmarked){
                    bookmarkText.set("Bookmarked, Tap to change")
                }else{
                    bookmarkText.set("Tap to bookmark")
                }
            },{
                Log.e("error : ", "${it}")
            })

        binding.media = media

    }

    fun onBookmarkTapped(){
        if(mediaObject?.isBookmarked == false){
            mediaObject?.isBookmarked = true
            repository.insert(mediaObject)
            bookmarkText.set("Bookmarked, Tap to change")


        }else{
            mediaObject?.isBookmarked = false
            repository.updateMedia(mediaObject!!)
            bookmarkText.set("Tap to bookmark")

        }


    }




}