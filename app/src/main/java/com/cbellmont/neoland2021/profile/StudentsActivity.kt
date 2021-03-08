package com.cbellmont.neoland2021.profile

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.neoland2021.databinding.ActivityProfileBinding
import com.cbellmont.neoland2021.model.entity.Student

class StudentsActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    private var adapter = StudentAdapter()
    private lateinit var model : StudentsActivityViewModel

    companion object {
        const val VALUE_1 = "VALOR_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(StudentsActivityViewModel::class.java)

        val email = intent.getStringExtra(VALUE_1)

        createRecyclerView()

        model.userList.observe(this){
            updateStudent(it)
            binding.progressBar.visibility = View.GONE
        }


        binding.progressBar.visibility = View.VISIBLE
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