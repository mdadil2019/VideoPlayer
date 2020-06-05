package com.mdadil2019.videoplayer.viewmodel

import android.app.Application
import android.provider.MediaStore
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.*
import com.mdadil2019.videoplayer.MediaRepository
import com.mdadil2019.videoplayer.VideoPlayer
import com.mdadil2019.videoplayer.adapter.VideoPlayerAdapter
import com.mdadil2019.videoplayer.callbacks.OnSnapPositionChangeListener
import com.mdadil2019.videoplayer.callbacks.SnapOnScrollListener
import com.mdadil2019.videoplayer.repo.model.Media


class VideoPlayerViewModel(private val app : Application) : AndroidViewModel(app), OnSnapPositionChangeListener{

    val videoPlayer : VideoPlayer =
        VideoPlayer(app.applicationContext)
    val adapter : VideoPlayerAdapter
    val repository : MediaRepository
    var snapView : View? = null
    init {
        repository = MediaRepository(app.applicationContext)

        adapter = VideoPlayerAdapter(
            getMediaFilesToPlay(), videoPlayer, repository
        )


    }

    companion object{
        var currentPosition : Int = 0
    }

    fun getMediaFilesToPlay(): List<Media>{
        val listOfMedia = arrayListOf<Media>()

        getAllMedia().forEach {
            listOfMedia.add(Media("Video Title",it!!,"thumbnail", "description"))
        }

        return listOfMedia
    }


    fun setAdapter(recyclerView: RecyclerView){
        setupLayout(recyclerView)
        recyclerView.adapter = adapter
    }

    fun setupLayout(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(app.applicationContext)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        val snapOnScrollListener = SnapOnScrollListener(snapHelper,onSnapPositionChangeListener = this)
        recyclerView.addOnScrollListener(snapOnScrollListener)

    }

    fun getAllMedia(): ArrayList<String?> {
        val videoItemHashSet: HashSet<String?> = HashSet()
        val projection = arrayOf(
            MediaStore.Video.VideoColumns.DATA,
            MediaStore.Video.Media.DISPLAY_NAME
        )
        val cursor = app.applicationContext.contentResolver
            .query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null)
        try {
            cursor?.moveToFirst()
            do {

                videoItemHashSet.add(cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)))
            } while (cursor?.moveToNext() != null && cursor.moveToNext())
            cursor?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList(videoItemHashSet)
    }

    fun onStop(){
     videoPlayer.stopVideo()
    }

    fun onResume(){
    videoPlayer.onResume()
    }



    override fun onCleared() {
        super.onCleared()
        videoPlayer.releaseVideo()
    }



    override fun onSnapPositionChange(position: Int) {
        currentPosition = position
    }


}