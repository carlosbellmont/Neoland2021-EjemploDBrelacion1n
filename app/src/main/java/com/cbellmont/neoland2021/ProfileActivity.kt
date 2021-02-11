package com.cbellmont.neoland2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cbellmont.neoland2021.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding

    companion object {
        const val VALUE_1 = "VALUE_1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(VALUE_1)
        binding.tvName.text = name
    }

}