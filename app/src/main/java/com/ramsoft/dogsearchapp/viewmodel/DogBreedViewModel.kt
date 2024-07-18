package com.ramsoft.dogsearchapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramsoft.dogsearchapp.database.FavoriteDog
import com.ramsoft.dogsearchapp.database.FavoriteDogDAO
import com.ramsoft.dogsearchapp.database.ViewLaterDog
import com.ramsoft.dogsearchapp.database.ViewLaterDogDao
import com.ramsoft.dogsearchapp.repository.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DogBreedViewModel @Inject constructor(private val repository: DogRepository, private val favoriteDogDAO: FavoriteDogDAO, private val viewLaterDogDao: ViewLaterDogDao) : ViewModel() {

    private val _breed = MutableStateFlow("boxer")
    private val _dogImage = MutableLiveData<String>()




    @OptIn(ExperimentalCoroutinesApi::class)
    private val _dogImages = _breed.flatMapLatest { breed ->
        repository.getImagesByBreed(breed)
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val dogImages: StateFlow<List<String>> get() = _dogImages
    fun setBreed(newBreed: String) {
        _breed.value = newBreed
    }

    fun addFavoriteImage(url: String) {
        viewModelScope.launch {
            favoriteDogDAO.insert(FavoriteDog(url = url, isFavorite = true))
        }
    }

    fun addViewLaterImage(url: String) {
        viewModelScope.launch {
            viewLaterDogDao.insert(ViewLaterDog(url = url, isViewLaterDog = true))
        }
    }

}

