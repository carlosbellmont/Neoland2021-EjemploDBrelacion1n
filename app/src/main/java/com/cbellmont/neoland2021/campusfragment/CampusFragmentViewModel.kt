package com.cbellmont.neoland2021.campusfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cbellmont.neoland2021.db.Db
import com.cbellmont.neoland2021.model.entity.Campus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CampusFragmentViewModel(application: Application) : AndroidViewModel(application) {



    val campusList : MutableLiveData<List<Campus>> = MutableLiveData()


    fun getAllCampus(){
        viewModelScope.launch(Dispatchers.IO) {
            changeUserListValueOnUi(Db.getDatabase(getApplication()).campusDao().getAll())
        }
    }


    private suspend fun changeUserListValueOnUi(campusList: List<Campus>) = withContext(Dispatchers.Main) {
        this@CampusFragmentViewModel.campusList.value = campusList
    }


}
