package com.cbellmont.neoland2021.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cbellmont.neoland2021.model.entity.Student


@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM Student")
    fun getAllLive(): LiveData<List<Student>>

    @Insert
    fun insert(student: Student)

    @Delete
    fun delete(student: Student)
}