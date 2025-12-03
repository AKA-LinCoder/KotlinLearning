package com.echo.kotlinlearning.file

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityFileBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class FileActivity : AppCompatActivity() {
    lateinit var binding: ActivityFileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val inputText = loadFile()
        if(inputText.isNotEmpty()){
            binding.editText.setText(inputText)
            binding.editText.setSelection(inputText.length)
        }
        binding.save.setOnClickListener {
            val editor = getSharedPreferences("user",MODE_PRIVATE).edit()
            editor.putString("name","echo")
            editor.apply()
        }
        binding.read.setOnClickListener {
            val sh = getSharedPreferences("user",MODE_PRIVATE)

            val name = sh.getString("name","")
            Toast.makeText(this,name, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = binding.editText.text.toString()
        save(inputText)
    }


    fun save(inoutText:String) {
        try {
            val output = openFileOutput("data", MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(inoutText)
            }
        }catch (e:Exception) {
            e.printStackTrace()
        }
    }

    fun loadFile():String{
        val content = StringBuilder()
        try {
            val inout = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(inout))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }

        }catch (e:Exception) {
            e.printStackTrace()
        }
        return content.toString()
    }

}