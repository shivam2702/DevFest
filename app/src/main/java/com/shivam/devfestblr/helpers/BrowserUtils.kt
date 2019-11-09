package com.shivam.devfestblr.helpers

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.shivam.devfestblr.R

fun openLink(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
        .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
        .setShowTitle(true)
        .addDefaultShareMenuItem()
        .build()
    builder.launchUrl(context, Uri.parse(url))
}
