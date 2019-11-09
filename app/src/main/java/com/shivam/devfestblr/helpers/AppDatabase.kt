package com.shivam.devfestblr.helpers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shivam.devfestblr.BuildConfig
import com.shivam.devfestblr.dao.*
import com.shivam.devfestblr.model.*

@Database(
    entities = [
        (Session::class),
        (Speaker::class),
        (Team::class),
        (Sponsor::class),
        (Favorites::class)
    ],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(
    ArrayListConverter::class,
    DateConverter::class,
    SocialListConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao
    abstract fun speakerDao(): SpeakerDao
    abstract fun teamDao(): TeamDao
    abstract fun sponsorDao(): SponsorDao
    abstract fun favouriteDao(): FavouriteDao

    fun syncAll() {
        Session.sync()
        Speaker.sync()
        Sponsor.sync()
        Team.sync()
    }

    fun essentialSync() {
        Favorites.sync()
    }

    companion object {
        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "GDGs")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return sInstance!!
        }
    }
}