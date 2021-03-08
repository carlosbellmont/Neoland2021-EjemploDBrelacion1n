package com.cbellmont.neoland2021.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cbellmont.neoland2021.model.entity.RegisteredUser


@Dao
interface RegisteredUserDao {


    @Query("SELECT * FROM RegisteredUser")
    fun getAllLive(): LiveData<List<RegisteredUser>>

    @Query("SELECT * FROM RegisteredUser")
    fun getAll(): List<RegisteredUser>

    @Insert
    fun insert(registeredUser: RegisteredUser)

    @Delete
    fun delete(registeredUser: RegisteredUser)
}