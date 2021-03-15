package com.cbellmont.neoland2021.request.model

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.cbellmont.neoland2021.model.entity.Student
import com.google.gson.annotations.SerializedName


interface StudentModelInterface {
    fun getNombreEnString() : String
    fun getFotoUrl() : String
    fun getEmailEnString() : String
}



data class StudentModel(
    @Embedded
    var name: Name,
    var gender: String,
    var email: String,
    @Embedded
    var picture: Picture,
) : StudentModelInterface {
    @PrimaryKey(autoGenerate = true)
    var userId = 0

    override fun getNombreEnString() : String {
        return String.format("%s %s", name.name, name.surname)
    }

    override fun getFotoUrl() : String {
        return picture.large
    }

    override fun getEmailEnString(): String {
        return email
    }

    fun toStudent() : Student {
        return Student(name.name + " " + name.surname, email, null)
    }
}



data class Name (
    @SerializedName("first") var name: String,
    @SerializedName("last") var surname: String
)
data class Picture (
    var large : String,
    var medium : String,
    var thumbnail : String
)