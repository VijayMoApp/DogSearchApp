package com.ramsoft.dogsearchapp.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        //  migration: adding a new column to the FavoriteDog table
        database.execSQL("ALTER TABLE FavoriteDog ADD COLUMN isFavorite TEXT")
    }
}