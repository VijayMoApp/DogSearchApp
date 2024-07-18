package com.ramsoft.dogsearchapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ramsoft.dogsearchapp.adapter.ViewLaterAdapter
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ramsoft.dogsearchapp.viewmodel.ViewImageLaterViewModel
import com.ramsoft.dogsearchapp.ui.base.BaseFragment
import com.ramsoft.dogsearchapp.databinding.FragmentViewLaterBinding
import dagger.hilt.android.AndroidEntryPoint


/*
   Display all view later Picture
 */
@AndroidEntryPoint
class ViewLaterFragment : BaseFragment<FragmentViewLaterBinding>() {

    private val viewModel: ViewImageLaterViewModel by viewModels()
    private lateinit var adapter: ViewLaterAdapter

    override fun inflatesBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentViewLaterBinding.inflate(inflater, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ViewLaterAdapter()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.contentDescription = "List of favorite dog images"


        viewModel.viewLaterImages.observe(viewLifecycleOwner) {
            adapter.setItems(it)
            Toast.makeText(requireContext(), "Updated favorites: ${it.size}", Toast.LENGTH_SHORT).show()
            Log.d("Favorite", "Updated favorites: $it")
        }
    }
}