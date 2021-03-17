package com.cbellmont.neoland2021.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cbellmont.neoland2021.studentsfragment.StudentsFragment
import com.cbellmont.neoland2021.databinding.ActivityMainBinding
import com.cbellmont.neoland2021.home.HomeActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var model: MainActivityViewModel
    lateinit var binding : ActivityMainBinding


    companion object {
        const val USER_NAME = "USER_NAME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.etLogin.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(textContent: Editable?) {
                textContent?.let {
                    // Quiero que el checkbox recordar usuario este deshabilitado hasta
                    // que en el texto no haya una "@" y un "."
                    binding.cbRemember.isEnabled = textContent.contains("@") && textContent.contains(".")
                }
            }
        })

        model.loadEmailPreferences()?.let {
            binding.cbRemember.isChecked = it.isNotEmpty()
            binding.etLogin.setText(it)
        }

        // Quiero que si se pulsa el botón "iniciar": si el checkedbos está activo, guarde el texto
        // que hay en el editext.
        // si el checkbox está desactivado o no marcado, entonces
        binding.bLogin.setOnClickListener {
            if (binding.cbRemember.isEnabled && binding.cbRemember.isChecked){
                model.saveEmailPreferences(binding.etLogin.text.toString())
            } else {
                model.saveEmailPreferences("")
            }
            // Ya que no sabemos de bases de datos aún. Vamos a simular un login de esta manera.
            lifecycleScope.launch {
                if (model.verifyUser(binding.etLogin.text.toString())) {
                    // Lanzamos la activity 2.
                    HomeActivity.create(this@MainActivity)
                } else {
                    Toast.makeText(this@MainActivity, "El usuario no está en la Base de Datos", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}