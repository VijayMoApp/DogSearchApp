package com.ramsoft.dogsearchapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_images")
data class FavoriteDog( @PrimaryKey val url: String ,
                         val isFavorite: Boolean)


