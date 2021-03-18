package com.cbellmont.neoland2021.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cbellmont.neoland2021.model.entity.Campus
import com.cbellmont.neoland2021.model.entity.Student


@Dao
interface CampusDao {

    @Query("SELECT * FROM Campus")
    fun getAll(): List<Campus>

    @Query("SELECT * FROM Campus")
    fun getAllLive(): LiveData<List<Campus>>

    @Insert
    fun insert(campus: Campus)

    @Insert
    fun insert(campus: List<Campus>)

    @Delete
    fun delete(campus: Campus)
}