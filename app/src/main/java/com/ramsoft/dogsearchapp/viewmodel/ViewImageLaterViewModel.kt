package com.ramsoft.dogsearchapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ramsoft.dogsearchapp.database.ViewLaterDogDao
import com.ramsoft.dogsearchapp.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ViewImageLaterViewModel @Inject constructor(private val repository: DogRepository, private val viewLaterDogDao: ViewLaterDogDao) : ViewModel() {

    val viewLaterImages = repository.setViewLaterDogImages()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
        .asLiveData()

}