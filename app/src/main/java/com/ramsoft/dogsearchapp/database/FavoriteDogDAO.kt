package com.ramsoft.dogsearchapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramsoft.dogsearchapp.database.FavoriteDog
import kotlinx.coroutines.flow.Flow



@Dao
interface FavoriteDogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogImage: FavoriteDog)

    @Query("SELECT * FROM favorite_images WHERE isFavorite = 1")
    fun getFavoriteImages(): Flow<List<FavoriteDog>>

    @Query("SELECT * FROM favorite_images LIMIT 1")
    suspend fun getFirstFavoriteDog(): FavoriteDog?

}
