package com.ramsoft.dogsearchapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import coil.load
import com.ramsoft.dogsearchapp.viewmodel.RandomDogViewModel


import com.ramsoft.dogsearchapp.ui.base.BaseFragment

import com.ramsoft.dogsearchapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel : RandomDogViewModel by activityViewModels()


    override fun inflatesBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater)


    override fun onViewCreated(view: View,
        savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        getData()


    }

private fun getData(){
    viewModel.dogImage.observe(viewLifecycleOwner, Observer { imageUrl ->
        if (imageUrl.isNotEmpty()) {
            // Load image using Coil
            binding.dogImageView.load(imageUrl)
        }
    })
}





}