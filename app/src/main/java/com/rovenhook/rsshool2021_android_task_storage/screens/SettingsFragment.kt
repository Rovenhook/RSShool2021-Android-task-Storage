package com.rovenhook.rsshool2021_android_task_storage.screens

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.rovenhook.rsshool2021_android_task_storage.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

    }

}