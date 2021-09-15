package com.rovenhook.rsshool2021_android_task_storage.screens

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.rovenhook.rsshool2021_android_task_storage.R
import com.rovenhook.rsshool2021_android_task_storage.databinding.FragmentAddAnimalBinding
import com.rovenhook.rsshool2021_android_task_storage.model.entities.Animal
import com.rovenhook.rsshool2021_android_task_storage.viewmodels.AnimalsViewModel


class AddAnimalFragment : Fragment() {
    private var _binding: FragmentAddAnimalBinding? = null
    private val binding: FragmentAddAnimalBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAnimalBinding.inflate(inflater, container, false)

        binding.buttonCancel.setOnClickListener {
            getBackToMainScreen()
        }

        binding.toolbar.setNavigationOnClickListener {
            getBackToMainScreen()
        }

        binding.toolbar.navigationIcon?.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);

        binding.buttonConfirm.setOnClickListener {
            val name: String = binding.editTextName.text.toString()
            val age: String = binding.editTextAge.text.toString()
            val breed: String = binding.editTextBreed.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && breed.isNotEmpty()) {
                val viewModel: AnimalsViewModel by viewModels()
                val animal = Animal(name, age.toInt(), breed)
                viewModel.addAnimal(animal)
                getBackToMainScreen()
            } else {
                Toast.makeText(activity, "All the fields must be filled", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    
    fun getBackToMainScreen() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, AnimalsListFragment()).commit()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}