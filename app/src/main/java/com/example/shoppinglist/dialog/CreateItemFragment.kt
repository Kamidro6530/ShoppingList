package com.example.shoppinglist.dialog

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.shoppinglist.R
import com.example.shoppinglist.home.HomeActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_create_item.view.*
import kotlin.random.Random

class CreateItemFragment : Fragment() {

    var firestore = FirebaseFirestore.getInstance()
    lateinit var image: String
    lateinit var save_data: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        image = arguments?.getString("image").toString()
        save_data = arguments?.getBundle("bundle") ?: Bundle()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inputFragmentView = inflater.inflate(R.layout.fragment_create_item, container, false)
        val enterButton = inputFragmentView.findViewById<Button>(R.id.alert_enter)
        val imageView = inputFragmentView.findViewById<ImageView>(R.id.alert_imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        if (image != "null") {
            Glide.with(parentFragment?.context).load(image).into(imageView)
        } else {
            imageView.background = context?.resources?.getDrawable(R.color.dark_blue)
        }

        //Set minimum,default and maximum count picker value
        inputFragmentView.alert_countNP.minValue = 0
        inputFragmentView.alert_countNP.value = 0
        inputFragmentView.alert_countNP.maxValue = 100

        //Enter data and send object to Cloud Firestore
        enterButton.setOnClickListener {
            val name = inputFragmentView.alert_nameET.text.toString()
            val count = inputFragmentView.alert_countNP.value.toFloat()
            val price = inputFragmentView.alert_priceET.text.toString().toFloat()
            val totalprice = price * count
            val randomNumber = Random.nextInt()
            //Mapping value to key
            val item = hashMapOf<String, String>()
            item["name"] = name
            item["path"] = name + "$count" + "$price" + "$randomNumber"
            item["count"] = count.toString()
            item["typeCount"] = "pln"
            item["price"] = price.toString()
            if (image != "null") {
                item["image"] = image
            } else {
                item["image"] = ""
            }
            item["total_price"] = totalprice.toString()

            firestore.collection("lists")
                .document(name + "$count" + "$price" + "$randomNumber")
                .set(item)
                .addOnFailureListener { exception ->
                    Log.d("TAG", "Add item to firestore error $exception")


                }
            //After add object come back to main acitvity
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent, null)

        }

        imageView.setOnClickListener {
            val datatoSave: Bundle? = bundleOf(
                "name" to inputFragmentView.alert_nameET.text.toString(),
                "count" to inputFragmentView.alert_countNP.value.toFloat(),
                "price" to inputFragmentView.alert_priceET.text.toString()
            )
            val bundle = bundleOf("bundle" to datatoSave)
            val action = CreateItemFragmentDirections.actionCreateItemFragmentToImageFragment()
            inputFragmentView.findNavController().navigate(action)
            Navigation.createNavigateOnClickListener(
                R.id.action_createItemFragment_to_imageFragment,
                bundle
            )
        }
        return inputFragmentView
    }
}