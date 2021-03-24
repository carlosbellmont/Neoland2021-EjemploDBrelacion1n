package com.cbellmont.neoland2021.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cbellmont.neoland2021.model.entity.CampusWithStudent
import com.cbellmont.neoland2021.model.entity.Student


@Dao
interface StudentDao {

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM Student")
    fun getAllLive(): LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(student: List<Student>)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM student INNER JOIN campus ON student.fkCampusId = campus.campusId")
    fun getStudentByCampus(): List<CampusWithStudent>
}