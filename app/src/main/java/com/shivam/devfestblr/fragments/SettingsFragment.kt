package com.shivam.devfestblr.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.*
import com.shivam.devfestblr.BuildConfig
import com.shivam.devfestblr.MyApplication
import com.shivam.devfestblr.R
import com.shivam.devfestblr.helpers.getDarkModeSetting
import com.shivam.devfestblr.helpers.openLink

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == getString(R.string.pref_dark_mode)) {
            MyApplication.getInstance().changeTheme()
            activity?.recreate()
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        val lastDarkMode = getDarkModeSetting()
        val darkMode = ListPreference(preferenceScreen.context)
        darkMode.title = "Dark Mode"
        darkMode.summary = when (lastDarkMode) {
            -1 -> "Auto"
            1 -> "Light"
            2 -> "Dark"
            else -> "Auto"
        }

        darkMode.entries = resources.getStringArray(R.array.dark_mode_array)
        darkMode.setDefaultValue("-1")
        darkMode.entryValues = resources.getStringArray(R.array.dark_mode_index)
        darkMode.key = getString(R.string.pref_dark_mode)
        preferenceScreen.addPreference(darkMode)


        val notificationMode = CheckBoxPreference(preferenceScreen.context)
        notificationMode.title = "Reminders"
        notificationMode.summary = "Notify for your favourite session / speakers"
        notificationMode.key = getString(R.string.pref_reminder)
        preferenceScreen.addPreference(notificationMode)


        val preferenceCategory = PreferenceCategory(preferenceScreen.context)
        preferenceCategory.title = "Open Source"

        preferenceScreen.addPreference(preferenceCategory)

        val github = Preference(preferenceScreen.context)
        github.title = "Project GitHub Link"
        github.setOnPreferenceClickListener {
            openLink(context!!, "https://www.google.com/")
            true
        }

        val documents = Preference(preferenceScreen.context)
        documents.title = "Project GitHub Link"
        documents.setOnPreferenceClickListener {
            openLink(context!!, "https://www.facebook.com/")
            true
        }

        val version = Preference(preferenceScreen.context)
        version.title = "Build ${BuildConfig.VERSION_NAME}"
        version.summary = "Version ${BuildConfig.VERSION_CODE}"
        version.setOnPreferenceClickListener {
            openLink(context!!, "https://twitter.com/")
            true
        }

        preferenceCategory.addPreference(github)
        preferenceCategory.addPreference(documents)
        preferenceCategory.addPreference(version)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}