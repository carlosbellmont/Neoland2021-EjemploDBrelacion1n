package com.cbellmont.neoland2021.students

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cbellmont.neoland2021.db.Db
import com.cbellmont.neoland2021.model.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentsActivityViewModel(application: Application) : AndroidViewModel(application) {

    val userList : MutableLiveData<List<Student>> = MutableLiveData()


    fun getAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = Db.getDatabase(getApplication()).studentDao().getAll()
            withContext(Dispatchers.Main) {
                userList.value = result
            }
        }
    }
}
