package com.echo.kotlinlearning

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FruitListView : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fruit_list_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        initFruitList()
        val adapter = FruitAdapter(this,R.layout.fruit_item,fruitList)
        val listView = findViewById<ListView>(R.id.listview1)
        listView.adapter = adapter
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val  fruit = fruitList[position]
                Toast.makeText(this,"You clicked ${fruit.name}", Toast.LENGTH_SHORT).show()

            }
    }

    private fun initFruitList(){
        repeat(2){
            fruitList.add(Fruit("Apple",R.drawable.fruit_06))
            fruitList.add(Fruit("Banana",R.drawable.fruit_09))
            fruitList.add(Fruit("Orange",R.drawable.food_04))
        }
    }

}

data class Fruit(val name:String,val imageId:Int)