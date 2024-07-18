package com.ramsoft.dogsearchapp.ui.fragment



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ramsoft.dogsearchapp.adapter.FavoriteAdapter
import com.ramsoft.dogsearchapp.ui.base.BaseFragment
import com.ramsoft.dogsearchapp.viewmodel.FavoriteViewModel
import com.ramsoft.dogsearchapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


/*
   Display all favorite Picture
 */
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavoriteAdapter

    override fun inflatesBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoriteBinding.inflate(inflater, container, false)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteAdapter()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.contentDescription = "List of favorite dog images"


        viewModel.favoriteDogImages.observe(viewLifecycleOwner) {
            adapter.setItems(it)
            Toast.makeText(requireContext(), "Updated favorites: ${it.size}", Toast.LENGTH_SHORT).show()
            Log.d("Favorite", "Updated favorites: $it")
        }
    }
}