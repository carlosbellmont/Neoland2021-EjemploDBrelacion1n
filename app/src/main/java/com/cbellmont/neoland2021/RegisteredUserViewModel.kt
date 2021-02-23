package com.cbellmont.neoland2021

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class RegisteredUserViewModel(application: Application) : AndroidViewModel(application) {

    private var myself = Student("Yo", "", R.mipmap.myself)

    suspend fun getAllUser(): List<Student> {
        delay(2000)
        val alumno1 = Student("Elena B", "e@b.com", R.mipmap.estudiante_femenina)
        val alumno2 = Student("Miguel A", "m@a.com", R.mipmap.estudiante)
        val alumno3 = Student("Belén C", "b@c.com", R.mipmap.estudiante_chica)
        val alumno4 = Student("Sergi E", "s@e.com", R.mipmap.chico)
        val alumno5 = Student("Adrián A", "e@b.com", R.mipmap.estudiante_chico)
        return listOf(myself, alumno1, alumno2, alumno3, alumno4,alumno5)
    }

    fun setMyselfEmail(email: String) {
        myself.email = email
    }

}