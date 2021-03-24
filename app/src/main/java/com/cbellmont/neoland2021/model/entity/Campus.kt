package com.cbellmont.neoland2021.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Campus(val campusName: String, val campusPhotoId: Int){
    @PrimaryKey(autoGenerate = true)
    var campusId : Int = 0
}