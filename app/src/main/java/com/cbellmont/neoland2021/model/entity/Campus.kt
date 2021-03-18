package com.cbellmont.neoland2021.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Campus(val name: String, val photoId: Int){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}