package com.rovenhook.rsshool2021_android_task_storage.model.sqlitedb

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rovenhook.rsshool2021_android_task_storage.utils.*


class AnimalsDbHelper(application: Application) : SQLiteOpenHelper(application, ANIMALS_DATABASE_NAME_SQLITE, null, ANIMALS_DATABASE_VERSION_SQLITE) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_COMMAND)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_COMMAND)
        onCreate(db)
    }
}
