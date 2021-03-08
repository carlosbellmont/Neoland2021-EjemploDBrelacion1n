package com.cbellmont.neoland2021.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cbellmont.neoland2021.App
import com.cbellmont.neoland2021.db.Db
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    fun loadEmailPreferences() : String? {
        val sharedPref = getApplication<App>().getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        return sharedPref.getString(MainActivity.USER_NAME, "")
    }

    fun saveEmailPreferences(string : String) {
        val sharedPref = getApplication<App>().getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(MainActivity.USER_NAME, string)
            commit()
        }
    }

    suspend fun verifyUser(email : String) : Boolean  {
        return viewModelScope.async(Dispatchers.IO) {
            val allRegisteredUser =  Db.getDatabase(getApplication()).registeredUserDao().getAll()
            allRegisteredUser.forEach {
                if (it.email.contentEquals(email))
                    return@async true
            }
            return@async false
        }.await()

    }


}