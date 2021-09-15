package com.rovenhook.rsshool2021_android_task_storage.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rovenhook.rsshool2021_android_task_storage.databinding.AnimalItemBinding
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.listeners.OnAnimalClickListener

class AnimalsAdapter(
    private val listener: OnAnimalClickListener
) : ListAdapter<Animal, AnimalsViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AnimalItemBinding.inflate(layoutInflater)
        return AnimalsViewHolder(listener, binding)
    }

    override fun onBindViewHolder(holder: AnimalsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val itemComparator = object : DiffUtil.ItemCallback<Animal>() {
            override fun areItemsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Animal, newItem: Animal): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.age == newItem.age &&
                        oldItem.breed == newItem.breed
            }
        }
    }
}