package com.cbellmont.neoland2021.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cbellmont.neoland2021.databinding.ActivitySplashBinding
import com.cbellmont.neoland2021.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(){

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val model = ViewModelProvider(this).get(SplashActivityViewModel::class.java)
        model.status.observe(this) {
            if (it.isNotEmpty() && it[0].created) {
                lifecycleScope.launch {
                    delay(2000)
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }



    }

}