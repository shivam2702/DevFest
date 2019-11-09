package com.shivam.devfestblr.helpers

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.shivam.devfestblr.MyApplication
import com.shivam.devfestblr.R

var MODE_NIGHT_FOLLOW_SYSTEM = "-1"

fun setOnlineDatabaseVersion(version: Long) {
    val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance())
    sharedPreferences.edit().putLong(
        MyApplication.getInstance().getString(R.string.pref_online_database_version),
        version
    ).apply()
}

fun getOnlineDatabaseVersion(): Long {
    val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance())
    return sharedPreferences.getLong(
        MyApplication.getInstance().getString(R.string.pref_online_database_version),
        0
    )
}

fun getDarkModeSetting(): Int {
    val sharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance())
    return sharedPreferences.getString(
        MyApplication.getInstance().getString(R.string.pref_dark_mode),
        MODE_NIGHT_FOLLOW_SYSTEM
    )!!.toInt()
}