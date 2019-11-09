package com.shivam.devfestblr.helpers

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat


fun Activity.openBrowser(url: String) {
    if (url.startsWith("http:")) url.replace("http:", "https:")
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    startActivity(intent)
}

fun Activity.openEmail(email: String) {
    ShareCompat.IntentBuilder.from(this)
        .setType("message/rfc822")
        .addEmailTo(email)
        .setSubject("")
        .setText("")
        .setChooserTitle("Send Email")
        .startChooser();
}