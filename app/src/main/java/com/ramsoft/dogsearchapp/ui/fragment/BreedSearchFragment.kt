package com.ramsoft.dogsearchapp.ui.fragment

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ramsoft.dogsearchapp.adapter.BreedsRecyclerViewAdapter

import com.ramsoft.dogsearchapp.ui.base.BaseFragment
import com.ramsoft.dogsearchapp.viewmodel.DogBreedViewModel
import com.ramsoft.dogsearchapp.viewmodel.RandomDogViewModel
import com.ramsoft.dogsearchapp.databinding.FragmentBreedSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.net.URL


/*
   Search image by Breed
   save image to local gallery
   share the image to other using social media , sms and email
   select favorite Picture
   collect view later image
 */
@AndroidEntryPoint
class BreedSearchFragment : BaseFragment<FragmentBreedSearchBinding>() {

    private val viewModel: DogBreedViewModel by viewModels()
    private lateinit var adapter: BreedsRecyclerViewAdapter
    private val randomViewModel : RandomDogViewModel by activityViewModels()

    override fun inflatesBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBreedSearchBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        getData()

        setupSearchView()
        val recyclerView: RecyclerView = binding.recyclerView

        adapter = BreedsRecyclerViewAdapter(
            onFavoriteClicked = { imageUrl ->
                viewModel.addFavoriteImage(imageUrl)
                Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
            },
            onViewLaterClicked = { imageUrl ->
                viewModel.addViewLaterImage(imageUrl)
                Toast.makeText(requireContext(), "Added to view later and saved to phone", Toast.LENGTH_SHORT).show()
            },
            onShareClicked = { imageUrl ->
                shareImage(imageUrl)
            },
            onSaveClicked = { imageUrl -> // Add this line
                saveImageToStorage(imageUrl) // Add this line

            }


        )

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = adapter


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.dogImages.collect { images ->
                adapter.updateImages(images)

            }
        }

    }
    private fun getData(){
        randomViewModel.dogImage.observe(viewLifecycleOwner, Observer { imageUrl ->
            if (imageUrl.isNotEmpty()) {
                // Load image using Coil
                binding.dogImageView.load(imageUrl)
            }
        })
    }
    private fun setupSearchView() {
        binding.searchView.queryHint = "Boxer"
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    val lowercaseQuery = it.lowercase()
                    viewModel.setBreed(lowercaseQuery)
                    Toast.makeText(requireContext(), lowercaseQuery, Toast.LENGTH_SHORT).show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun shareImage(imageUrl: String) {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check out this dog picture: $imageUrl\nPowered by Dog API app")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share image via"))
    }

    private fun saveImageToStorage(imageUrl: String) {
        lifecycleScope.launch {
            try {
                val bitmap = withContext(Dispatchers.IO) {
                    val url = URL(imageUrl)
                    BitmapFactory.decodeStream(url.openConnection().getInputStream())
                }
                val filename = "dog_${System.currentTimeMillis()}.jpg"
                val fos: OutputStream?

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val resolver: ContentResolver = requireContext().contentResolver
                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }
                    val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                    fos = imageUri?.let { resolver.openOutputStream(it) }
                } else {
                    val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    if (!imagesDir.exists()) {
                        imagesDir.mkdirs()
                    }
                    val image = File(imagesDir, filename)
                    fos = FileOutputStream(image)
                }

                fos?.use {
                    if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Image saved to gallery", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Failed to compress image", Toast.LENGTH_SHORT).show()
                        }
                    }
                } ?: throw IOException("Failed to create output stream")
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to save image: ${e.message}", Toast.LENGTH_SHORT).show()
                }
                e.printStackTrace()
            }
        }
    }

}