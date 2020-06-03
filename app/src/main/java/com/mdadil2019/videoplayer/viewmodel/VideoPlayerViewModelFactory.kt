package com.mdadil2019.videoplayer.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class VideoPlayerViewModelFactory(private var app : Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideoPlayerViewModel(app) as T
        }


}