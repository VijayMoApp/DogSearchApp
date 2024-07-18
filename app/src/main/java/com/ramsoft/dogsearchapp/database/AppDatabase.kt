package com.ramsoft.dogsearchapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

// change Export schema = false
@Database(entities = [FavoriteDog::class , ViewLaterDog::class]  , version = 1 , exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteDogDAO(): FavoriteDogDAO

    abstract fun viewLaterDogDao(): ViewLaterDogDao
}