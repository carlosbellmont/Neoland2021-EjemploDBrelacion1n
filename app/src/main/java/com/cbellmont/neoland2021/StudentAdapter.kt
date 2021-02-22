package com.cbellmont.neoland2021

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbellmont.neoland2021.databinding.ItemStudentBinding

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private var studentList =  listOf<Student>()

    class StudentViewHolder(val itemBinding: ItemStudentBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemBinding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.itemBinding.ivPhoto.setImageResource(student.photo)
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
