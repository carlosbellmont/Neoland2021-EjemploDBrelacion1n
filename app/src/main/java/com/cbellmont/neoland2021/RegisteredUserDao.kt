package com.cbellmont.neoland2021

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface RegisteredUserDao {

    @Query("SELECT * FROM RegisteredUser")
    fun getAll(): List<RegisteredUser>

    @Insert
    fun insert(registeredUser: RegisteredUser)

    @Delete
    fun delete(registeredUser: RegisteredUser)
}