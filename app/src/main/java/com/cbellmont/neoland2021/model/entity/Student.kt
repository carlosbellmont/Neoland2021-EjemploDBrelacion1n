package com.cbellmont.neoland2021.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(val name: String, var email : String, val photo : Int){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}