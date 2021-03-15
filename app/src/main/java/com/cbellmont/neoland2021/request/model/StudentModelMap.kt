package com.cbellmont.neoland2021.request.model

import com.cbellmont.neoland2021.model.entity.Student

class StudentModelMap {

    companion object{
        fun map(studentModelList : List<StudentModel>) : List<Student>  {
            val listStudent  = mutableListOf<Student>()
            studentModelList.forEach { studentModel ->
                listStudent.add(studentModel.toStudent())
            }
            return listStudent
        }
    }
}