package com.rovenhook.rsshool2021_android_task_storage.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rovenhook.rsshool2021_android_task_storage.utils.*

@Entity(tableName = ANIMALS_TABLE_NAME)
data class Animal(

    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_AGE)
    val age: Int,

    @ColumnInfo(name = COLUMN_BREED)
    val breed: String,

    @ColumnInfo(name = COLUMN_ID)
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0)