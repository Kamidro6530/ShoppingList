package com.example.shoppinglist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.database.ShopItem
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class RecyclerAdapter(var list : ArrayList<ShopItem>) : RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view =  LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
       // Picasso.get().load(list[position].image).into(holder.image)
        holder.name.text = list[position].name
        holder.count.text ="${holder.count.text}  ${ list[position].count?.toFloat()}"
        holder.price.text =  "${holder.price.text}  ${ list[position].price.toString()}"
         var totalprice = (list[position].count?.toFloat())!! * (list[position].price?.toFloat())!!
       holder.totalPrice.text = "${holder.totalPrice.text}  $totalprice"

    }

}
class MyHolder(view: View) : RecyclerView.ViewHolder(view){
    var image = view.findViewById<ImageView>(R.id.item_image)
    var name  = view.findViewById<TextView>(R.id.item_name)
    var count = view.findViewById<TextView>(R.id.item_count)
    var price = view.findViewById<TextView>(R.id.item_price)
    var totalPrice = view.findViewById<TextView>(R.id.item_total_price)

}