package com.rovenhook.rsshool2021_android_task_storage.model.repositories

import androidx.lifecycle.LiveData
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal

abstract class AbstractRepository {
    abstract fun getAllAnimals() : LiveData<List<Animal>>

    abstract suspend fun updateAnimal(animal: Animal)

    abstract suspend fun deleteAnimal(animal: Animal)

    abstract suspend fun addAnimal(animal: Animal)
}