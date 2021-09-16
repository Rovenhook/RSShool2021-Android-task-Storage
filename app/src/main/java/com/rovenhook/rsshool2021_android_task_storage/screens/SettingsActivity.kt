package com.rovenhook.rsshool2021_android_task_storage.screens

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.rovenhook.rsshool2021_android_task_storage.MainActivity
import com.rovenhook.rsshool2021_android_task_storage.R
import com.rovenhook.rsshool2021_android_task_storage.databinding.ActivityMainBinding
import com.rovenhook.rsshool2021_android_task_storage.databinding.ActivitySettingsBinding
import com.rovenhook.rsshool2021_android_task_storage.viewmodels.AnimalsViewModel

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, SettingsFragment()).commit()


        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.toolbar.navigationIcon?.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);

    }

    override fun onBackPressed() {
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)

        super.onBackPressed()
    }
}