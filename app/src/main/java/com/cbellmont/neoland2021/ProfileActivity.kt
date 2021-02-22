package com.cbellmont.neoland2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.neoland2021.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    private var adapter = StudentAdapter()
    private var myself = Student("Yo", "", R.mipmap.myself)

    companion object {
        const val VALUE_1 = "VALOR_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra(VALUE_1)
        email?.let {
            myself.email = email
        }
        createRecyclerView()

    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        adapter.updateData(getStudentList())
    }


    private fun getStudentList() : List<Student> {
        val alumno1 = Student("Elena B", "e@b.com", R.mipmap.estudiante_femenina)
        val alumno2 = Student("Miguel A", "m@a.com", R.mipmap.estudiante)
        val alumno3 = Student("Belén C", "b@c.com", R.mipmap.estudiante_chica)
        val alumno4 = Student("Sergi E", "s@e.com", R.mipmap.chico)
        val alumno5 = Student("Elena B", "e@b.com", R.mipmap.estudiante_chico)

        return listOf(myself, alumno1, alumno2, alumno3, alumno4,alumno5)
    }

}