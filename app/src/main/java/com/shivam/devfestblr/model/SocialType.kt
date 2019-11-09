package com.shivam.devfestblr.model

object SocialType {
    var WEB = 0
    var EMAIL = 1

    fun createEmailProfile(value: String): Social = Social(
        "Email",
        "https://cdn.iconscout.com/icon/free/png-256/email-1578817-1346636.png",
        EMAIL,
        value
    )

    fun createPhoneProfile(value: String): Social = Social(
        "Phone",
        "https://purepng.com/public/uploads/large/purepng.com-phone-icon-android-kitkatsymbolsiconsapp-iconsandroid-kitkatandroid-44-721522597658ivuve.png",
        WEB,
        value
    )

    fun createFacebookProfile(value: String): Social = Social(
        "Facebook",
        "https://cdn2.iconfinder.com/data/icons/popular-social-media-flat/48/Popular_Social_Media-01-512.png",
        WEB,
        value
    )

    fun createTwitterProfile(value: String): Social = Social(
        "Twitter",
        "https://pluspng.com/img-png/twitter-logo-png-twitter-logo-vector-png-clipart-library-518.png",
        WEB,
        value
    )

    fun createLinkedInProfile(value: String): Social = Social(
        "LinkedIn",
        "https://pngriver.com/wp-content/uploads/2018/04/Download-Linkedin-PNG-HD.png",
        WEB,
        value
    )

    fun createBehanceProfile(value: String): Social = Social(
        "Behance",
        "https://www.searchpng.com/wp-content/uploads/2019/01/Behance-Logo-PNG-Transparent-1024x1024.png",
        WEB,
        value
    )

    fun createDribbleProfile(value: String): Social = Social(
        "Dribble",
        "https://www.logolynx.com/images/logolynx/62/62305956876ae3c4ece06155d9754e03.png",
        WEB,
        value
    )

    fun createGitHubProfile(value: String): Social = Social(
        "GitHub",
        "https://cdn3.iconfinder.com/data/icons/sociocons/256/github-sociocon.png",
        WEB,
        value
    )
}