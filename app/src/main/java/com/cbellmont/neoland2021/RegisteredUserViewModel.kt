package com.cbellmont.neoland2021

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.delay

class RegisteredUserViewModel(application: Application) : AndroidViewModel(application) {

    private var myself = Student("Yo", "", R.mipmap.myself)

    suspend fun getAllUser(): List<Student> {
        val alumno1 = Student("Elena B", "e@b.com", R.mipmap.estudiante_femenina)
        delay(400)
        val alumno2 = Student("Miguel A", "m@a.com", R.mipmap.estudiante)
        delay(400)
        val alumno3 = Student("Belén C", "b@c.com", R.mipmap.estudiante_chica)
        delay(400)
        val alumno4 = Student("Sergi E", "s@e.com", R.mipmap.chico)
        delay(400)
        val alumno5 = Student("Adrián A", "e@b.com", R.mipmap.estudiante_chico)
        delay(400)

        return listOf(myself, alumno1, alumno2, alumno3, alumno4,alumno5)
    }

    fun setMyselfEmail(email: String) {
        myself.email = email
    }

}