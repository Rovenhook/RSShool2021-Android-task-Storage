package com.rovenhook.rsshool2021_android_task_storage.utils

const val ANIMALS_DATABASE: String = "animals_database.db"
const val ANIMALS_DATABASE_NAME_ROOM: String = "animals_database_room.db"
const val ANIMALS_DATABASE_NAME_SQLITE: String = "animals_database_sqlite.db"
const val ANIMALS_DATABASE_VERSION_ROOM: Int = 1
const val ANIMALS_DATABASE_VERSION_SQLITE: Int = 1
const val ANIMALS_TABLE_NAME: String = "animals_table"
const val COLUMN_NAME: String = "name"
const val COLUMN_AGE: String = "age"
const val COLUMN_BREED: String = "breed"
const val COLUMN_ID: String = "id"

const val QUERY_SELECT_ALL_ANIMALS = "SELECT * FROM $ANIMALS_TABLE_NAME"

const val CREATE_TABLE_COMMAND = "CREATE TABLE IF NOT EXISTS $ANIMALS_TABLE_NAME " +
        "($COLUMN_NAME TEXT, $COLUMN_AGE INTEGER, $COLUMN_BREED TEXT" +
        ", $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT)"

const val DROP_TABLE_COMMAND = "DROP TABLE IF EXISTS $ANIMALS_TABLE_NAME"

const val ROOM_MODE: Boolean = false
const val SQLite_MODE: Boolean = true

const val KEY_SORT_BY: String = "preference_sort_by"
const val KEY_ORM_MODE: String = "preference_room_sqlite"



