package com.shivam.devfestblr.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivam.devfestblr.model.Sponsor

@Dao
interface SponsorDao {
    @Query("SELECT * from sponsor order by name")
    fun getList(): LiveData<List<Sponsor>>

    @Query("SELECT * from sponsor WHERE id = :id LIMIT 1")
    fun getInfo(id: String): LiveData<Sponsor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Sponsor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ArrayList<Sponsor>)

    @Query("DELETE from sponsor")
    fun deleteAll()
}