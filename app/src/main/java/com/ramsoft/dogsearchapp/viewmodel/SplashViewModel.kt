package com.ramsoft.dogsearchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramsoft.dogsearchapp.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DogRepository
) : ViewModel() {

    private val _imageUrl = MutableStateFlow<String?>(null)
    val imageUrl: StateFlow<String?> = _imageUrl

    init {
        viewModelScope.launch {
            val favoriteDog = repository.getFavoriteDog()
            if (favoriteDog != null) {
                _imageUrl.value = favoriteDog.url
            } else {
                val randomImage = repository.getRandomDog()
                _imageUrl.value = randomImage
            }
        }
    }
}