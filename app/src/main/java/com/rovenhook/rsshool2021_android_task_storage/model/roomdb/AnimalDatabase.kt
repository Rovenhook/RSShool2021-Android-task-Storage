package com.rovenhook.rsshool2021_android_task_storage.model.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.utils.ANIMALS_DATABASE_VERSION_ROOM

@Database(entities = arrayOf(Animal::class), version = ANIMALS_DATABASE_VERSION_ROOM)
abstract class AnimalDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}