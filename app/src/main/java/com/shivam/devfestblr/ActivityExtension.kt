package com.shivam.devfestblr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.start(context: Context) {
    startActivity(Intent(context, this::class.java))
}