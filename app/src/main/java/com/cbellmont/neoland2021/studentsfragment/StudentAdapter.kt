package com.cbellmont.neoland2021.studentsfragment

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cbellmont.neoland2021.databinding.ItemStudentBinding
import com.cbellmont.neoland2021.model.entity.Student
import com.squareup.picasso.Picasso


interface StudentAdapterInterface {
    fun onItemClick(student : Student)
}

class StudentAdapter(private val listener : StudentAdapterInterface): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

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
        student.imageUrl?.let { Picasso.get().load(it).into(holder.itemBinding.ivPhoto) }

        holder.itemBinding.tvEmail.text = student.email
        holder.itemBinding.tvName.text = student.name

        holder.itemBinding.root.setOnClickListener {
            listener.onItemClick(student)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateData(studentList : List<Student>){
        this.studentList = studentList
        notifyDataSetChanged()
    }

}
