package com.rovenhook.rsshool2021_android_task_storage.model.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.model.roomdb.AnimalDatabase
import com.rovenhook.rsshool2021_android_task_storage.utils.ANIMALS_DATABASE_NAME_ROOM

class RepositoryRoom(application: Application) : AbstractRepository()  {
    val roomDatabase = Room.databaseBuilder(
        application,
        AnimalDatabase::class.java, ANIMALS_DATABASE_NAME_ROOM
    ).build()
    val animalDao = roomDatabase.animalDao()
    lateinit var animals: LiveData<List<Animal>>

    override fun getAllAnimals(): LiveData<List<Animal>> {
        animals = animalDao.getAllAnimals()
        return animals
    }

    override suspend fun updateAnimal(animal: Animal) {
        animalDao.update(animal)
    }

    override suspend fun deleteAnimal(animal: Animal) {
        animalDao.delete(animal)
    }

    override suspend fun addAnimal(animal: Animal) {
        animalDao.insert(animal)
    }
}