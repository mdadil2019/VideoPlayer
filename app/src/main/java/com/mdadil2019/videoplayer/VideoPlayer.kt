package com.mdadil2019.videoplayer

import android.content.Context
import android.media.session.PlaybackState
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import java.io.File


class VideoPlayer(private val context : Context) {
    private val exoPlayer : ExoPlayer
    var playerView : PlayerView? = null

    init {
        exoPlayer = ExoPlayerFactory.newSimpleInstance(context,DefaultTrackSelector())
    }

    fun playVideo(playerView : PlayerView, url : String) {
        this.playerView = playerView
        playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
        playerView.player = exoPlayer
        playerView.requestFocus()

        val dataSourceFactory = DefaultDataSourceFactory(
            context, Util.getUserAgent(context, "VideoPlayer")
        )
        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))

        exoPlayer.prepare(videoSource)
        exoPlayer.playWhenReady = true


    }

    fun releaseVideo(){
        exoPlayer.release()
    }

    fun stopVideo(){
        exoPlayer.stop()
    }




}