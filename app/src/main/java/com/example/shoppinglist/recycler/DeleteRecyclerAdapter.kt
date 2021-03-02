package com.example.shoppinglist.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppinglist.R
import com.example.shoppinglist.database.ShopItem


class DeleteRecyclerAdapter(var list: ArrayList<ShopItem>) :
    RecyclerView.Adapter<MyDeleteHolder>() {

    var checked_list = ArrayList<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDeleteHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.delete_list_item, parent, false)
        return MyDeleteHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size

    }

    fun addCheckedItem(position: Int): ArrayList<ShopItem> {
        checked_list.add(list[position])
        return checked_list
    }

    fun removeCheckedItem(position: Int): ArrayList<ShopItem> {
        checked_list.remove(list[position])
        return checked_list
    }

    override fun onBindViewHolder(holder: MyDeleteHolder, position: Int) {
        holder.itemView.apply {
            Glide.with(context).load(list[position].image).into(holder.image)
            holder.image.scaleType = ImageView.ScaleType.FIT_XY
        }

        holder.name.text = list[position].name
        holder.count.text = "${holder.count.text}  ${list[position].count?.toFloat()}"
        holder.price.text = "${holder.price.text}  ${list[position].price.toString()}"
        var totalprice = (list[position].count?.toFloat())!! * (list[position].price?.toFloat())!!
        holder.totalPrice.text = "${holder.totalPrice.text}  $totalprice"
        holder.path.text = list[position].path

        holder.checkbox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {

                if (holder.checkbox.isChecked) {
                    holder.itemView.setBackgroundResource(R.drawable.delete_list_item_background)
                    addCheckedItem(position)
                } else {
                    holder.itemView.setBackgroundResource(R.drawable.list_item_background)
                    removeCheckedItem(position)

                }
            }

        })


    }

}

class MyDeleteHolder(view: View) : RecyclerView.ViewHolder(view) {
    var image: ImageView = view.findViewById<ImageView>(R.id.delete_item_image)
    var name = view.findViewById<TextView>(R.id.delete_item_name)
    var count = view.findViewById<TextView>(R.id.delete_item_count)
    var price = view.findViewById<TextView>(R.id.delete_item_price)
    var totalPrice = view.findViewById<TextView>(R.id.delete_item_total_price)
    var path = view.findViewById<TextView>(R.id.delete_item_path)
    var checkbox = view.findViewById<CheckBox>(R.id.delete_checkBox)

}