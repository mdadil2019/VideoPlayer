package com.mdadil2019.videoplayer.repo.dao

import androidx.room.*
import com.mdadil2019.videoplayer.repo.model.Media
import io.reactivex.Single

@Dao
interface MediaDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMedia(media : Media)

    @Query("SELECT * FROM Media where mediaUrl = :url")
    fun getMedia(url : String): Single<Media>

    @Delete
    fun deleteMedia(media : Media)

    @Update
    fun updateMedia(media : Media)
}