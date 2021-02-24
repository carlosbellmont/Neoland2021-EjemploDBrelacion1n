package com.cbellmont.neoland2021

import android.os.Bundle
import android.view.View
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
        updateUserStepByStep()
    }


    // Esta función devuelve todos los elementos a la vez
    private fun updateAllUSerAtOnce(){
        lifecycleScope.launch{
            binding.progressBar.visibility = View.VISIBLE
            val users = model.getAllUser()
            adapter.updateData(users)
            binding.progressBar.visibility = View.GONE
        }
    }

    // Esta función devuelve los elementos de uno en uno
    private fun updateUserStepByStep(){
        lifecycleScope.launch{
            binding.progressBar.visibility = View.VISIBLE
            for (i in 0 until model.numberOfUser()){
                val user = model.getUser(i)
                adapter.updateData(user)
            }
            binding.progressBar.visibility = View.GONE
        }
    }


}