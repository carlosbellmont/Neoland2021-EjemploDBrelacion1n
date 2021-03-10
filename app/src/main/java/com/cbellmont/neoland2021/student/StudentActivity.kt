package com.cbellmont.neoland2021.student

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cbellmont.neoland2021.databinding.ActivityStudentBinding
import kotlinx.coroutines.launch


class StudentActivity : AppCompatActivity(){

    private lateinit var binding : ActivityStudentBinding
    private lateinit var model : ActivityStudentViewModel

    private var bitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(ActivityStudentViewModel::class.java)

        binding.selectPicture.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }

        binding.bSaveStudent.setOnClickListener {
            lifecycleScope.launch {
                model.insertStudent(
                    binding.etTextPersonName.text.toString(),
                    binding.etEmailAddress.text.toString(),
                    bitmap
                )
                finish()
            }
        }
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            uri?.let { uri ->
                contentResolver?.let { contentResolver ->
                    bitmap = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        MediaStore.Images.Media.getBitmap( this.contentResolver, uri)
                    }
                }
            }
            binding.ivUserImage.setImageBitmap(bitmap) //  handle chosen image
        }
    }



}