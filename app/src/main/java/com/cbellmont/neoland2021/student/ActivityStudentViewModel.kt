package com.cbellmont.neoland2021.student

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import com.cbellmont.neoland2021.db.Db
import com.cbellmont.neoland2021.model.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class ActivityStudentViewModel(application: Application) : AndroidViewModel(application) {
    suspend fun insertStudent(name: String, email: String, bitmap: Bitmap?) {
        withContext(Dispatchers.IO) {
            val student = Student(name, email, 0)
            bitmap?.let {
                val stream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                student.image = stream.toByteArray()
            }
            Db.getDatabase(getApplication()).studentDao().insert(student)
        }
    }
}