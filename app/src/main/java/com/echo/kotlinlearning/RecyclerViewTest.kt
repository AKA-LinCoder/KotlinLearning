package com.echo.kotlinlearning

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewTest : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()
    private lateinit var aa: List<Fruit>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_test)
        initFruits()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter2(fruitList)
        recyclerView.adapter = adapter
        if(!::aa.isInitialized){
            Log.e("erq", "aa is null")
        }
    }


    private fun initFruits() {
        repeat(50) {
            fruitList.add(Fruit("asdas", R.drawable.fruit_09))
        }
    }

}

sealed class Result
class Success(val data: String) : Result()
class Failure(val error: String) : Result()