package com.ramsoft.dogsearchapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramsoft.dogsearchapp.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomDogViewModel @Inject constructor(private val repo: DogRepository) : ViewModel() {


    private val _dogImage = MutableLiveData<String>()
    val dogImage: LiveData<String> get() = _dogImage

    init {
        fetchRandomDogImage()
    }
    fun fetchRandomDogImage(){
        viewModelScope.launch {
            try {
                _dogImage.value = repo.getRandomDog()
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
}