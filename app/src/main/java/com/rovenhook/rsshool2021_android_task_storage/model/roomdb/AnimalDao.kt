package com.rovenhook.rsshool2021_android_task_storage.model.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.utils.QUERY_SELECT_ALL_ANIMALS

@Dao
interface AnimalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(animal: Animal)

    @Delete
    suspend fun delete(animal: Animal)

    @Update
    suspend fun update(animal: Animal)

    @Query(QUERY_SELECT_ALL_ANIMALS)
    fun getAllAnimals(): LiveData<List<Animal>>
}