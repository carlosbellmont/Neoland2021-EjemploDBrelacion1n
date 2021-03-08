package com.cbellmont.neoland2021.splash

import android.app.Application
import androidx.lifecycle.*
import com.cbellmont.neoland2021.db.Db
import com.cbellmont.neoland2021.model.entity.DbStatus

class SplashActivityViewModel(application: Application) : AndroidViewModel(application) {

    val status : LiveData<List<DbStatus>> = liveData { emitSource(Db.getDatabase(application).dbStatusDao().getAllLive()) }

}