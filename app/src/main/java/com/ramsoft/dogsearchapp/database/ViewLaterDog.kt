package com.ramsoft.dogsearchapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "view_later_images")
data class ViewLaterDog(@PrimaryKey val url: String,
                        val isViewLaterDog: Boolean)
