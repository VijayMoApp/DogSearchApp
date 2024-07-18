package com.ramsoft.dogsearchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ramsoft.dogsearchapp.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(repository: DogRepository) : ViewModel() {
    val favoriteDogImages = repository.setFavoriteDogImages()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        .asLiveData()
}


