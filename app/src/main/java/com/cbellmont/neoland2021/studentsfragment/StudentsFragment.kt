package com.cbellmont.neoland2021.studentsfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.neoland2021.databinding.FragmentStudentsBinding
import com.cbellmont.neoland2021.model.entity.Student
import com.cbellmont.neoland2021.student.StudentActivity

class StudentsFragment : Fragment(), StudentAdapterInterface {

    private lateinit var binding : FragmentStudentsBinding
    private var adapter = StudentAdapter(this)
    private lateinit var model : StudentsFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(StudentsFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStudentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRecyclerView()

        model.userList.observe(viewLifecycleOwner){ students ->
            updateStudent(students)
            binding.pbLoading.visibility = View.GONE
        }

        model.status.observe(viewLifecycleOwner) {
            when (it) {
                StudentsFragmentViewModel.DownloadStatus.STARTED -> binding.pbLoading.visibility = View.VISIBLE
                StudentsFragmentViewModel.DownloadStatus.FINISHED -> binding.pbLoading.visibility = View.GONE
            }
        }

        binding.fbAdd.setOnClickListener {
            val i = Intent(binding.root.context, StudentActivity::class.java)
            startActivity(i)
        }

        binding.fbDownload.setOnClickListener {
            model.downloadUser()
        }

        binding.pbLoading.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        model.getAllUser()
    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        binding.recyclerView.adapter = adapter
    }

    // Esta función devuelve todos los elementos a la vez
    private fun updateStudent(students : List<Student>){
        adapter.updateData(students)
    }

    override fun onItemClick(student : Student) {
        val builder = AlertDialog.Builder(binding.root.context)
        builder.setMessage("¿Seguro que quieres borrar?")
                .setPositiveButton("Sí") { dialog, id ->
                    model.deleteStudent(student)
                    Toast.makeText(binding.root.context, "${student.name} ha sido eliminado", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("No") { dialog, id ->
                    Toast.makeText(binding.root.context, "No se ha borrado a nadie", Toast.LENGTH_LONG).show()
                }
        builder.create()
        builder.show()
    }

}