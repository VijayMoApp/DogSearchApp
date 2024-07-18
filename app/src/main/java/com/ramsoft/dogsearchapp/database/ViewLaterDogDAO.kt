package com.ramsoft.dogsearchapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramsoft.dogsearchapp.database.ViewLaterDog
import kotlinx.coroutines.flow.Flow


@Dao
interface ViewLaterDogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(viewLaterDog: ViewLaterDog)

    @Delete
    suspend fun delete(viewLaterDog: ViewLaterDog)

    @Query("SELECT * FROM view_later_images WHERE isViewLaterDog = 1")
    fun getViewLaterDogs(): Flow<List<ViewLaterDog>>
}