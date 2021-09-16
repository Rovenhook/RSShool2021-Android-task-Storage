package com.rovenhook.rsshool2021_android_task_storage.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rovenhook.rsshool2021_android_task_storage.R
import com.rovenhook.rsshool2021_android_task_storage.adapters.AnimalsAdapter
import com.rovenhook.rsshool2021_android_task_storage.databinding.FragmentAnimalsListBinding
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.viewmodels.AnimalsViewModel
import com.rovenhook.rsshool2021_android_task_storage.listeners.OnAnimalClickListener
import com.rovenhook.rsshool2021_android_task_storage.utils.COLUMN_ID
import com.rovenhook.rsshool2021_android_task_storage.utils.KEY_ORM_MODE
import com.rovenhook.rsshool2021_android_task_storage.utils.KEY_SORT_BY
import com.rovenhook.rsshool2021_android_task_storage.utils.ROOM_MODE


class AnimalsListFragment : Fragment() {
    private var _binding: FragmentAnimalsListBinding? = null
    private val binding: FragmentAnimalsListBinding get() = _binding!!
    private val viewModel: AnimalsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalsListBinding.inflate(inflater, container, false)

        val adapter = AnimalsAdapter(getOnAnimalClickListener())
        binding.recyclerViewAnimals.adapter = adapter
        binding.recyclerViewAnimals.layoutManager = LinearLayoutManager(activity)

        val preferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        val databaseMode: Boolean = preferences.getBoolean(KEY_ORM_MODE, ROOM_MODE)
        viewModel.changeRepositoryType(databaseMode)

        val sortBy = preferences.getString(KEY_SORT_BY, "Time added")
        binding.toolbar.subtitle = "Sorted by ${sortBy?.lowercase()}"

        viewModel.getAllAnimals().observe(viewLifecycleOwner, Observer {
            val list = when (sortBy) {
                "Time added" -> it.sortedBy { id }
                "Name" -> it.sortedBy { it.name }
                "Age" -> it.sortedBy { it.age }
                else -> it.sortedBy { it.breed }
            }
            adapter.submitList(list)
        })

        binding.floatingActionButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, AddAnimalFragment()).commit()
        }

        binding.buttonSettings.setOnClickListener {
            val intent = Intent(requireActivity(), SettingsActivity::class.java)
            startActivity(intent)

//            requireActivity().supportFragmentManager.beginTransaction()
//                .replace(R.id.fragmentContainerView, SettingsFragment()).commit()

        }

        return binding.root
    }

    fun getOnAnimalClickListener() = object : OnAnimalClickListener {
        override fun onDeleteClicked(animal: Animal) {
            viewModel.deleteAnimal(animal)
        }

        override fun onEditClicked(animal: Animal) {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, EditAnimalFragment(animal)).commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}