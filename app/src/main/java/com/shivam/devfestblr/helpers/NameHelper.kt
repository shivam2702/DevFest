package com.shivam.devfestblr.helpers

fun String.getInitials(): String {
    val initials = StringBuilder()
    for (s in this.split(" ")) {
        initials.append(s[0])
    }
    return initials.toString()
}