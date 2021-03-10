package com.cbellmont.neoland2021.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Student(val name: String, var email: String, val photoId: Int?,  @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (name != other.name) return false
        if (email != other.email) return false
        if (photoId != other.photoId) return false
        if (!image.contentEquals(other.image)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (photoId ?: 0)
        result = 31 * result + image.contentHashCode()
        result = 31 * result + id
        return result
    }
}