package com.cbellmont.neoland2021

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class RegisteredUserViewModel(application: Application) : AndroidViewModel(application) {

    private var myself = Student("Yo", "", R.mipmap.myself)
    private val lista = listOf(myself,
            Student("Elena B", "e@b.com", R.mipmap.estudiante_femenina),
            Student("Miguel A", "m@a.com", R.mipmap.estudiante),
            Student("Belén C", "b@c.com", R.mipmap.estudiante_chica),
            Student("Sergi E", "s@e.com", R.mipmap.chico),
            Student("Adrián A", "e@b.com", R.mipmap.estudiante_chico))
    fun numberOfUser() = lista.size


    suspend fun getAllUser(): List<Student> {
        return viewModelScope.async {
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

            listOf(myself, alumno1, alumno2, alumno3, alumno4,alumno5)
        }.await()
    }

    suspend fun getUser(number : Int): Student {
        return viewModelScope.async {
            delay(1000)
            lista[number]
        }.await()

    }

    fun setMyselfEmail(email: String) {
        myself.email = email
    }

}