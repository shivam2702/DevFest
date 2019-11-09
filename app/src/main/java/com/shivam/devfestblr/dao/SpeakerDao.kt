package com.shivam.devfestblr.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivam.devfestblr.model.Speaker


@Dao
interface SpeakerDao {
    @Query("SELECT * from speaker order by name")
    fun getList(): LiveData<List<Speaker>>

    @Query("SELECT * from speaker WHERE id IN (:myList) order by name")
    fun getList(myList: List<String>): List<Speaker>

    @Query("SELECT * from speaker WHERE id = :id LIMIT 1")
    fun getInfo(id: String): LiveData<Speaker>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Speaker)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ArrayList<Speaker>)

    @Query("DELETE from speaker")
    fun deleteAll()
}