package com.cbellmont.neoland2021.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cbellmont.neoland2021.model.entity.DbStatus


@Dao
interface DbStatusDao {

    @Query("SELECT * FROM DbStatus")
    fun getAll(): List<DbStatus>

    @Query("SELECT * FROM DbStatus")
    fun getAllLive(): LiveData<List<DbStatus>>

    @Insert
    fun insert(dbStatus: DbStatus)

    @Delete
    fun delete(dbStatus: DbStatus)
}