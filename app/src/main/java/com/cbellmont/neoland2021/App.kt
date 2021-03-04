package com.cbellmont.neoland2021

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)
    }


}
