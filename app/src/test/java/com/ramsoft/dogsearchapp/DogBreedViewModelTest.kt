package com.ramsoft.dogsearchapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ramsoft.dogsearchapp.database.FavoriteDog
import com.ramsoft.dogsearchapp.database.FavoriteDogDAO
import com.ramsoft.dogsearchapp.database.ViewLaterDog
import com.ramsoft.dogsearchapp.database.ViewLaterDogDao
import com.ramsoft.dogsearchapp.repository.DogRepository
import com.ramsoft.dogsearchapp.viewmodel.DogBreedViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations



/*



testSetBreed: Verify that setting a breed updates the StateFlow.

testGetDogImages: Verify fetching dog images based on a breed.

testAddFavoriteImage: Verify adding an image to the favorites.

testAddViewLaterImage: Verify adding an image to the "view later" list.



 */










@OptIn(ExperimentalCoroutinesApi::class)
class DogBreedViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Mock
    private lateinit var repository: DogRepository

    @Mock
    private lateinit var favoriteDogDAO: FavoriteDogDAO

    @Mock
    private lateinit var viewLaterDogDao: ViewLaterDogDao

    private lateinit var viewModel: DogBreedViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DogBreedViewModel(repository, favoriteDogDAO, viewLaterDogDao)
    }

    @Test
    fun testSetBreed() = testScope.runBlockingTest {
        // When
        viewModel.setBreed("labrador")

        // Then
        assertEquals("labrador", viewModel.dogImages.value.firstOrNull())
    }

    @Test
    fun testGetDogImages() = testScope.runBlockingTest {
        // Given
        val breed = "boxer"
        val dogImages = listOf("image1.jpg", "image2.jpg")
        `when`(repository.getImagesByBreed(breed)).thenReturn(flowOf(dogImages))

        // When
        viewModel.setBreed(breed)

        // Then
        assertEquals(dogImages, viewModel.dogImages.value)
    }

    @Test
    fun testAddFavoriteImage() = testScope.runBlockingTest {
        // Given
        val imageUrl = "https://example.com/image.jpg"

        // When
        viewModel.addFavoriteImage(imageUrl)

        // Then
        verify(favoriteDogDAO).insert(FavoriteDog(url = imageUrl, isFavorite = true))
    }

    @Test
    fun testAddViewLaterImage() = testScope.runBlockingTest {
        // Given
        val imageUrl = "https://example.com/image.jpg"

        // When
        viewModel.addViewLaterImage(imageUrl)

        // Then
        verify(viewLaterDogDao).insert(ViewLaterDog(url = imageUrl, isViewLaterDog = true))
    }
}