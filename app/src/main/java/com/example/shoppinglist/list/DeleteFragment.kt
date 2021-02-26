package com.example.shoppinglist.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.mvvm.ListViewModel
import com.example.shoppinglist.recycler.DeleteRecyclerAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_delete.*


class DeleteFragment : Fragment() {


    var firestore = FirebaseFirestore.getInstance()
    val listViewModel by viewModels<ListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val inputFragmentView = inflater.inflate(R.layout.fragment_delete, container, false)
    listViewModel.list.observe(viewLifecycleOwner, Observer { it ->
        var  adapter = DeleteRecyclerAdapter(it)
        val recyclerView = view?.findViewById(R.id.delete_recyclerView) as? RecyclerView
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity?.applicationContext,
            LinearLayoutManager.VERTICAL, false)
        recyclerView?.setHasFixedSize(true)

      delete_fab_deleteButton.setOnClickListener {

          adapter.checked_list.forEach {
            firestore.collection("lists").document(it.path.toString().replace("/","_")).delete()

          }
          val action = DeleteFragmentDirections.actionDeleteFragmentToListFragment()
          inputFragmentView.findNavController().navigate(action)
          Navigation.createNavigateOnClickListener(R.id.action_deleteFragment_to_listFragment,null)

      }

        })
        return inputFragmentView

    }


    }
