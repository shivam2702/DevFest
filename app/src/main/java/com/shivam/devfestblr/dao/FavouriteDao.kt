package com.shivam.devfestblr.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivam.devfestblr.model.Favorites

@Dao
interface FavouriteDao {
    @Query("SELECT * from favorites")
    fun getList(): LiveData<List<Favorites>>

    @Query("SELECT * from favorites WHERE id = :id")
    fun isLiked(id: String): List<Favorites>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Favorites: Favorites)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Favorites: ArrayList<Favorites>)

    @Query("DELETE from favorites")
    fun deleteAll()

    @Query("DELETE from favorites where id = :id")
    fun delete(id: String)
}