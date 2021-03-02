package com.example.shoppinglist.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoppinglist.R
import com.example.shoppinglist.pixabay.ImageResponse

class ImageRecyclerAdapter(var list: List<ImageResponse>, var bundle: Bundle) :
    RecyclerView.Adapter<ImageHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ImageHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {


        holder.itemView.apply {
            Glide.with(context).load(list[position].previewURL).into(holder.image)
        }

        holder.itemView.setOnClickListener {
            val bundle = bundleOf("image" to list[position].previewURL, "bundle" to bundle)
            Navigation.createNavigateOnClickListener(
                R.id.action_imageFragment_to_createItemFragment,
                bundle
            )
            holder.itemView.findNavController()
                .navigate(R.id.action_imageFragment_to_createItemFragment, bundle)

        }
    }

}

class ImageHolder(view: View) : RecyclerView.ViewHolder(view) {

    var image = view.findViewById<ImageView>(R.id.imageItem_imageView)
}