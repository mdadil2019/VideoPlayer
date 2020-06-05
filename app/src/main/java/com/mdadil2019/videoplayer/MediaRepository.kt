package com.mdadil2019.videoplayer

import android.content.Context
import androidx.room.Room
import com.mdadil2019.videoplayer.repo.MyDatabase
import com.mdadil2019.videoplayer.repo.model.Media
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

class MediaRepository(private val context : Context) {
    private val db : MyDatabase = Room.databaseBuilder(context,MyDatabase::class.java,MyDatabase.DB_NAME).build()

    fun queryMedia(url : String): Single<Media> {
        return db.mediaDao().getMedia(url)
    }

    fun insert(media : Media?){
        Completable.fromCallable(Callable {
            return@Callable db.mediaDao().addMedia(media!!)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

    }

    fun updateMedia(media: Media){

        Completable.fromCallable(Callable{
            return@Callable db.mediaDao().updateMedia(media)
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
//        db.mediaDao().updateMedia(media)
    }
//    fun remove(media : Media){
//        db.mediaDao().deleteMedia(media)
//    }

}