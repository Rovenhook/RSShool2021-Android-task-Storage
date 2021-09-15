package com.rovenhook.rsshool2021_android_task_storage.model.repositories

import android.app.Application
import android.content.ContentValues
import android.database.Cursor
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.model.sqlitedb.AnimalsDbHelper
import com.rovenhook.rsshool2021_android_task_storage.utils.*

class RepositorySQLite(application: Application) : AbstractRepository() {
    val dbHelper = AnimalsDbHelper(application)
    val sqLiteDatabase = dbHelper.writableDatabase
    val animals: LiveData<List<Animal>> = MutableLiveData<List<Animal>>()

    fun updateLiveData() {
        val tempList: ArrayList<Animal> = ArrayList<Animal>()
        val cursor: Cursor = sqLiteDatabase.query(
            ANIMALS_TABLE_NAME, null, null, null, null, null, null, null
        )

        while (cursor.moveToNext()) {
            val name: String = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            val age: Int = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
            val breed: String = cursor.getString(cursor.getColumnIndex(COLUMN_BREED))
            val id: Int = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            tempList.add(Animal(name, age, breed, id))
        }
        cursor.close()
        (animals as MutableLiveData).value = tempList
    }

    override fun getAllAnimals(): LiveData<List<Animal>> {
        if (animals.value.isNullOrEmpty()) {
            updateLiveData()
        }
        return animals
    }

    override suspend fun updateAnimal(animal: Animal) {
        val whereClause: String = "$COLUMN_ID = ?"
        val whereArgs: Array<String> = arrayOf(animal.id.toString())
        val newValues = ContentValues()
            newValues.apply {
                put(COLUMN_NAME, animal.name)
                put(COLUMN_AGE, animal.age)
                put(COLUMN_BREED, animal.breed)
        }
        sqLiteDatabase.update(ANIMALS_TABLE_NAME, newValues, whereClause, whereArgs)
        updateLiveData()
    }

    override suspend fun deleteAnimal(animal: Animal) {
        val whereClause: String = "$COLUMN_ID = ?"
        val whereArgs: Array<String> = arrayOf(animal.id.toString())
        sqLiteDatabase.delete(ANIMALS_TABLE_NAME, whereClause, whereArgs)
        updateLiveData()
    }

    override suspend fun addAnimal(animal: Animal) {
        val contentValues: ContentValues = ContentValues()
        contentValues.apply {
            put(COLUMN_NAME, animal.name)
            put(COLUMN_AGE, animal.age)
            put(COLUMN_BREED, animal.breed)
        }
        sqLiteDatabase.insert(ANIMALS_TABLE_NAME, null, contentValues)
        updateLiveData()
    }
}