package com.cbellmont.neoland2021.students

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbellmont.neoland2021.databinding.ItemStudentBinding
import com.cbellmont.neoland2021.model.entity.Student

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentList =  listOf<Student>()

    class StudentViewHolder(val itemBinding: ItemStudentBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemBinding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        student.photoId?.let { holder.itemBinding.ivPhoto.setImageResource(it) }
        student.image?.let { holder.itemBinding.ivPhoto.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size)) }

        holder.itemBinding.tvEmail.text = student.email
        holder.itemBinding.tvName.text = student.name
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateData(studentList : List<Student>){
        this.studentList = studentList
        notifyDataSetChanged()
    }

}
