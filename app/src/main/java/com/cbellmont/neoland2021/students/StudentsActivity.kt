package com.cbellmont.neoland2021.students

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.neoland2021.databinding.ActivityStudentsBinding
import com.cbellmont.neoland2021.model.entity.Student
import com.cbellmont.neoland2021.student.StudentActivity

class StudentsActivity : AppCompatActivity() {

    lateinit var binding : ActivityStudentsBinding
    private var adapter = StudentAdapter()
    private lateinit var model : StudentsActivityViewModel

    companion object {
        const val VALUE_1 = "VALOR_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(StudentsActivityViewModel::class.java)

        val email = intent.getStringExtra(VALUE_1)

        createRecyclerView()

        model.userList.observe(this){
            updateStudent(it)
            binding.progressBar.visibility = View.GONE
        }

        binding.fbAdd.setOnClickListener {
            val i = Intent(this, StudentActivity::class.java)
            startActivity(i)
        }

        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        model.getAllUser()
    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
    
    // Esta función devuelve todos los elementos a la vez
    private fun updateStudent(students : List<Student>){
        adapter.updateData(students)
    }

}