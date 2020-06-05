package com.mdadil2019.videoplayer.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mdadil2019.videoplayer.repo.dao.MediaDao
import com.mdadil2019.videoplayer.repo.model.Media


@Database(entities = [Media::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun mediaDao() : MediaDao

    companion object{
        private val LOCK = Any()
        val DB_NAME = "videoplayer.db"

        @Volatile private var instance : MyDatabase? = null
        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            MyDatabase::class.java, DB_NAME)
            .build()
    }
}