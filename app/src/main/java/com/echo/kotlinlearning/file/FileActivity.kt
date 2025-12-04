package com.echo.kotlinlearning.file

import android.content.ContentValues
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
        val dbHelper = DBHelper(this,"book.db",1)
        binding.createdb.setOnClickListener {
            dbHelper.writableDatabase
        }
        binding.insert.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("name","mike")
                put("author","echo")
                put("pages",432)
                put("price",23.3)
                }
            db.insert("Book",null,values)
            val values2 = ContentValues().apply {
                put("name","tom")
                put("author","siya")
                put("pages",432)
                put("price",63.3)
            }
            db.insert("Book",null,values2)
            }
        binding.readdb.setOnClickListener {
            val db = dbHelper.readableDatabase
            val cursor = db.query("Book",null,null,null,null,null,null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("echo", "name is $name")
                } while (cursor.moveToNext())
            }
        }
        binding.updatedata.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("pages", 433)
                put("price", 23.4)
            }
            db.update("Book",values,"name = ?", arrayOf("mike"))
        }
        binding.deletedata.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book","name = ?", arrayOf("mike"))
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