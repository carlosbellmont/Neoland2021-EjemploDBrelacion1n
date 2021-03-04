package com.cbellmont.neoland2021

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    fun loadEmailPreferences() : String? {
        val sharedPref = getApplication<App>().getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        return sharedPref.getString(MainActivity.USER_NAME, "")
    }

    fun saveEmailPreferences(string : String) {
        val sharedPref = getApplication<App>().getSharedPreferences("Preferencias", Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(com.cbellmont.neoland2021.MainActivity.USER_NAME, string)
            commit()
        }
    }

    suspend fun verifyUser(email : String) : Boolean  {
        return viewModelScope.async(Dispatchers.IO) {
            val allRegisteredUser =  Db.getDatabase(getApplication()).registredUserDao().getAll()
            allRegisteredUser.forEach {
                if (it.email.contentEquals(email))
                    return@async true
            }
            return@async false
        }.await()

    }


}