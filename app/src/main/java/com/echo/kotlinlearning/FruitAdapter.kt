package com.echo.kotlinlearning

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(activity: Activity,val resourceId:Int,data:List<Fruit>): ArrayAdapter<Fruit>(activity,resourceId,data) {

    inner class ViewHolder(val fruitImage:ImageView,val fruitName:TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder:ViewHolder
        if (convertView == null){
            view = LayoutInflater.from(context).inflate(resourceId,parent,false)
            val fruitImage:ImageView = view.findViewById<ImageView>(R.id.fruit_image)
            val fruitName:TextView = view.findViewById<TextView>(R.id.fruit_name)
            viewHolder = ViewHolder(fruitImage,fruitName)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }




        val fruit = getItem(position)
        if (fruit != null){
            viewHolder.fruitImage.setImageResource(fruit.imageId)
            viewHolder.fruitName.text = fruit.name
        }
        return view
    }
}

class FruitAdapter2(val fruitList: List<Fruit>): RecyclerView.Adapter<FruitAdapter2.FruitViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FruitViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item,parent,false)
        return FruitViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FruitViewHolder,
        position: Int
    ) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    inner class FruitViewHolder(view: View):RecyclerView.ViewHolder(view){
        val fruitImage:ImageView = view.findViewById(R.id.fruit_image)
        val fruitName:TextView = view.findViewById(R.id.fruit_name)
    }

}