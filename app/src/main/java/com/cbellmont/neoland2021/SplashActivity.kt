package com.cbellmont.neoland2021

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cbellmont.neoland2021.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(){

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(2000)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}