package com.cbellmont.neoland2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cbellmont.neoland2021.databinding.ActivityProfileBinding
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    private var adapter = StudentAdapter()
    private lateinit var model :RegisteredUserViewModel

    companion object {
        const val VALUE_1 = "VALOR_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(RegisteredUserViewModel::class.java)

        val email = intent.getStringExtra(VALUE_1)
        email?.let {
            model.setMyselfEmail(email)
        }
        createRecyclerView()

    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        lifecycleScope.launch{
            adapter.updateData(model.getAllUser())
        }
    }



}