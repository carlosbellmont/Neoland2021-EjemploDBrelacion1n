package com.cbellmont.neoland2021.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cbellmont.neoland2021.R
import com.cbellmont.neoland2021.campusfragment.CampusFragment
import com.cbellmont.neoland2021.databinding.ActivityHomeBinding
import com.cbellmont.neoland2021.studentsfragment.StudentsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    companion object{
        fun create(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(StudentsFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_1 -> {
                    Log.d(HomeActivity::class.java.name, "Botón 1")
                    changeFragment(StudentsFragment())
                    Toast.makeText(this, "Menu 1", Toast.LENGTH_LONG).show()
                }
                R.id.menu_2 -> {
                    Log.d(HomeActivity::class.java.name, "Botón 2")
                    changeFragment(CampusFragment())
                    Toast.makeText(this, "Menu 2", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Log.e(HomeActivity::class.java.name, "Unknown item on navigationView")
                    return@setOnNavigationItemSelectedListener false
                }
            }
            true
        }
    }

    private fun changeFragment(fragment : Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.lframe.id, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}