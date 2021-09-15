package com.rovenhook.rsshool2021_android_task_storage.listeners

import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal

interface OnAnimalClickListener {
    fun onDeleteClicked(animal: Animal)

    fun onEditClicked(animal: Animal)
}