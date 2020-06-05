package com.mdadil2019.videoplayer.repo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Media (


    var title : String,

    @PrimaryKey

    var mediaUrl : String,
    var thumbnail : String,
    var description : String,

    var isBookmarked : Boolean = false
)