package com.cbellmont.neoland2021

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.ims.RegistrationManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.cbellmont.neoland2021.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding


    companion object {
        const val USER_NAME = "USER_NAME"
    }

    // Usuario temporal hasta que tengamos una base de datos creada
    val usuarioValido = RegisteredUser("carlos@neoland.com")

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
            // Ya que no sabemos de bases de datos aún. Vamos a simular un login de esta manera.
            if (binding.etLogin.text.toString().contentEquals(usuarioValido.email)) {
                // Lanzamos la activity 2.
                startProfileActivity()
            } else {
                Toast.makeText(this, "El usuario no está en la Base de Datos", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun startProfileActivity(){
        val user = RegisteredUser("aaaa")
        user.email = "este otro email"

        // Esto estaríoa muy muy mal
        // var activity = ProfileActivity()
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.VALUE_1, binding.etLogin.text.toString())
        startActivity(intent)
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