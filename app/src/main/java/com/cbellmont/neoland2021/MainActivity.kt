package com.cbellmont.neoland2021

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.cbellmont.neoland2021.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    companion object {
        const val USER_NAME = "USER_NAME"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        cargarPreferencias()?.let {
            binding.cbRemember.isChecked = it.isNotEmpty()
            binding.etLogin.setText(it)
        }

        // Quiero que si se pulsa el botón "iniciar": si el checkedbos está activo, guarde el texto
        // que hay en el editext.
        // si el checkbox está desactivado o no marcado, entonces
        binding.bLogin.setOnClickListener {
            if (binding.cbRemember.isEnabled && binding.cbRemember.isChecked){
                guardarPreferencias(binding.etLogin.text.toString())
            } else {
                guardarPreferencias("")
            }
        }


    }

    private fun cargarPreferencias() : String? {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getString(USER_NAME, "")
    }

    private fun guardarPreferencias(string : String) {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        with (sharedPref.edit()) {
            putString(USER_NAME, string)
            commit()
        }
    }

}