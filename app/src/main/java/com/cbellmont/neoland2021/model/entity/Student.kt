package com.cbellmont.neoland2021.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(foreignKeys = [ForeignKey(entity = Campus::class,
    parentColumns = arrayOf("campusId"), // La id se corresponde con Campus.campusId
    childColumns = arrayOf("fkCampusId"), // la foreing key se corresponde con fkCampusId
    onDelete = ForeignKey.SET_NULL)]
)
data class Student(val studentName: String, var email: String, val studentPhotoId: Int?, @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null, var imageUrl : String? = null, var fkCampusId : Int? = null){
    @PrimaryKey(autoGenerate = true)
    var studentId : Int = 0


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        if (studentName != other.studentName) return false
        if (email != other.email) return false
        if (studentPhotoId != other.studentPhotoId) return false
        if (!image.contentEquals(other.image)) return false
        if (studentId != other.studentId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = studentName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + (studentPhotoId ?: 0)
        result = 31 * result + image.contentHashCode()
        result = 31 * result + studentId
        return result
    }
}