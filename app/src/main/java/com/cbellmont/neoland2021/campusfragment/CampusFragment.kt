package com.cbellmont.neoland2021.campusfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cbellmont.neoland2021.databinding.FragmentCampusBinding
import com.cbellmont.neoland2021.studentsfragment.StudentsFragmentViewModel

class CampusFragment : Fragment(){

    private lateinit var binding : FragmentCampusBinding
    private lateinit var model : CampusFragmentViewModel
    private val adapter = CampusAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProvider(this).get(CampusFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCampusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createGirdRecyclerView()
        model.campusList.observe(viewLifecycleOwner){ campus ->
            adapter.updateData(campus)
        }

        model.getAllCampus()
    }


    private fun createGirdRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(binding.root.context, 2)
        binding.recyclerView.adapter = adapter
    }
}