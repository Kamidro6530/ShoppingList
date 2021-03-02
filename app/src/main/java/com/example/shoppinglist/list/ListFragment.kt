package com.example.shoppinglist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.mvvm.ListViewModel
import com.example.shoppinglist.recycler.RecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {


    var isFABOpen = false
    val listViewModel by viewModels<ListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inputFragmentView = inflater.inflate(R.layout.fragment_list, container, false)
        val fab1 = inputFragmentView.findViewById(R.id.fab1) as FloatingActionButton
        val fab2 = inputFragmentView.findViewById(R.id.fab2) as FloatingActionButton
        val fab3 = inputFragmentView.findViewById(R.id.fab3) as FloatingActionButton
        val fab = inputFragmentView.findViewById(R.id.fab) as FloatingActionButton

        fab.setOnClickListener {
            if (!isFABOpen) {
                showFABMenu()
            } else {
                closeFABMenu()
            }
        }
        fab1.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToCreateItemFragment()
            inputFragmentView.findNavController().navigate(action)
            Navigation.createNavigateOnClickListener(
                R.id.action_listFragment_to_createItemFragment,
                null
            )

        }
        fab2.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDeleteFragment()
            inputFragmentView.findNavController().navigate(action)
            Navigation.createNavigateOnClickListener(
                R.id.action_listFragment_to_deleteFragment,
                null
            )
        }
        fab3.setOnClickListener {
            Toast.makeText(activity, "Edit", Toast.LENGTH_SHORT).show()
        }


        listViewModel.list.observe(viewLifecycleOwner, Observer { it ->
            var adapter = RecyclerAdapter(it)
            val recyclerView = view?.findViewById(R.id.recyclerView) as? RecyclerView
            recyclerView?.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(
                activity?.applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            var totalprice = 0.0
            it.forEach { totalprice += it.price?.toFloat()!! * it.count?.toFloat()!! }
            totalprice_valueTV.text = totalprice.toString()
            recyclerView?.setHasFixedSize(true)
        })
        return inputFragmentView
    }


    private fun showFABMenu() {
        isFABOpen = true
        fab1?.animate()?.translationY(-resources.getDimension(R.dimen.standard_55))
        fab2?.animate()?.translationY(-resources.getDimension(R.dimen.standard_105))
        fab3?.animate()?.translationY(-resources.getDimension(R.dimen.standard_155))
    }

    private fun closeFABMenu() {
        isFABOpen = false
        fab1?.animate()?.translationY(0F)
        fab2?.animate()?.translationY(0F)
        fab3?.animate()?.translationY(0F)
    }


}

