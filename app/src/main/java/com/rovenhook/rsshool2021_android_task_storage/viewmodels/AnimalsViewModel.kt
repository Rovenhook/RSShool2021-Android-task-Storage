package com.rovenhook.rsshool2021_android_task_storage.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.preference.PreferenceManager
import com.rovenhook.rsshool2021_android_task_storage.model.repositories.AbstractRepository
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.model.repositories.RepositoryRoom
import com.rovenhook.rsshool2021_android_task_storage.model.repositories.RepositorySQLite
import com.rovenhook.rsshool2021_android_task_storage.utils.KEY_ORM_MODE
import com.rovenhook.rsshool2021_android_task_storage.utils.ROOM_MODE
import com.rovenhook.rsshool2021_android_task_storage.utils.SQLite_MODE
import kotlinx.coroutines.launch

class AnimalsViewModel(application: Application) : AndroidViewModel(application) {
    val preferences = PreferenceManager.getDefaultSharedPreferences(application)
    val squliteMode: Boolean = preferences.getBoolean(KEY_ORM_MODE, ROOM_MODE)
    private var repositoryMode: Boolean = if (true) SQLite_MODE else ROOM_MODE

    private var repository: AbstractRepository = if (squliteMode) {
        repositoryMode = SQLite_MODE
        RepositorySQLite(getApplication())
    } else {
        repositoryMode = ROOM_MODE
        RepositoryRoom(getApplication())
    }

    fun getRepositoryMode() = repositoryMode

    fun changeRepositoryType(switchToSql: Boolean) {
        if (switchToSql != repositoryMode) {
            repository = if (switchToSql == SQLite_MODE) {
                repositoryMode = SQLite_MODE
                RepositorySQLite(getApplication())
            } else {
                repositoryMode = ROOM_MODE
                RepositoryRoom(getApplication())
            }
        }
    }

    fun getAllAnimals(): LiveData<List<Animal>> {
        lateinit var animalsLiveData: LiveData<List<Animal>>
        viewModelScope.launch {
            animalsLiveData = repository.getAllAnimals()
        }

        return animalsLiveData
    }

    fun addAnimal(animal: Animal) {
        viewModelScope.launch {
            repository.addAnimal(animal)
        }
    }

    fun deleteAnimal(animal: Animal) {
        viewModelScope.launch {
            repository.deleteAnimal(animal)
        }
    }

    fun updateAnimal(animal: Animal) {
        viewModelScope.launch {
            repository.updateAnimal(animal)
        }
    }
}