package com.rovenhook.rsshool2021_android_task_storage.adapters

import androidx.recyclerview.widget.RecyclerView
import com.rovenhook.rsshool2021_android_task_storage.databinding.AnimalItemBinding
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.listeners.OnAnimalClickListener

class AnimalsViewHolder(
    val listener: OnAnimalClickListener,
    private val binding: AnimalItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(animal: Animal) {
        binding.textViewName.text = "Name: ${animal.name}"
        binding.textViewAge.text = "Age: ${animal.age.toString()}"
        binding.textViewBreed.text = "Breed: ${animal.breed}"

        binding.buttonEdit.setOnClickListener {
            listener.onEditClicked(animal)
        }

        binding.imageViewDelete.setOnClickListener {
            listener.onDeleteClicked(animal)
        }
    }

}