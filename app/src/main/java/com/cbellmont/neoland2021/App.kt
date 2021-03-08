package com.cbellmont.neoland2021

import android.app.Application
import com.cbellmont.neoland2021.db.Db


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)
    }


}
