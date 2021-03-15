package com.cbellmont.neoland2021.students

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cbellmont.neoland2021.db.Db
import com.cbellmont.neoland2021.model.entity.Student
import com.cbellmont.neoland2021.request.GetAllUsers
import com.cbellmont.neoland2021.request.model.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentsActivityViewModel(application: Application) : AndroidViewModel(application) {



    val userList : MutableLiveData<List<Student>> = MutableLiveData()
    val status : MutableLiveData<DownloadStatus> = MutableLiveData()

    enum class DownloadStatus {
        FINISHED, STARTED
    }

    fun downloadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            changeDownloadStatusOnUi(DownloadStatus.STARTED)
            Db.getDatabase(getApplication()).studentDao().insert(GetAllUsers.send())
            changeUserListValueOnUi(Db.getDatabase(getApplication()).studentDao().getAll())
            changeDownloadStatusOnUi(DownloadStatus.FINISHED)
        }
    }

    fun getAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            changeUserListValueOnUi(Db.getDatabase(getApplication()).studentDao().getAll())
        }
    }

    private suspend fun changeDownloadStatusOnUi(downloadStatus: DownloadStatus) = withContext(Dispatchers.Main) {
        status.value = downloadStatus
    }

    private suspend fun changeUserListValueOnUi(studentList: List<Student>) = withContext(Dispatchers.Main) {
        userList.value = studentList
    }

}
