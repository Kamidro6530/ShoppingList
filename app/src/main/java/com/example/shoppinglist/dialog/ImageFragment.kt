package com.example.shoppinglist.dialog


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.R
import com.example.shoppinglist.mvvm.ListViewModel
import com.example.shoppinglist.pixabay.ImageList
import com.example.shoppinglist.recycler.ImageRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.home_activity.*
import java.util.*


class ImageFragment : Fragment() {


    val viewmodel by viewModels<ListViewModel>()
    lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = arguments?.getBundle("bundle") ?: Bundle()

    }

    lateinit var adapter: ImageRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inputFragmentView = inflater.inflate(R.layout.fragment_image, container, false)
        var image_searchView = inputFragmentView.findViewById<SearchView>(R.id.image_searchView)

        RefreshImages()

        image_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    val receivedText = query.toLowerCase(Locale.getDefault())


                    viewmodel.searchImages(receivedText)

                    RefreshImages()

                } else {
                    RefreshImages()

                }

                return true
            }


        })



        return inputFragmentView
    }

    fun RefreshImages() {

        viewmodel.getListImages()?.observe(
            viewLifecycleOwner,
            Observer<ImageList> { imageList ->
                if (imageList != null) {

                    adapter = ImageRecyclerAdapter(imageList.hits, bundle)

                    image_recyclerView.adapter = adapter
                    image_recyclerView.layoutManager =
                        GridLayoutManager(fragment.context, 3, LinearLayoutManager.VERTICAL, false)
                    image_recyclerView?.setHasFixedSize(true)
                }
            })
    }

}