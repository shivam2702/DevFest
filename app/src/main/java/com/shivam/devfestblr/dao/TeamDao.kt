package com.shivam.devfestblr.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivam.devfestblr.model.Team

@Dao
interface TeamDao {
    @Query("SELECT * from team order by name")
    fun getList(): LiveData<List<Team>>

    @Query("SELECT * from team WHERE id = :id LIMIT 1")
    fun getInfo(id: String): LiveData<Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Team)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ArrayList<Team>)

    @Query("DELETE from team")
    fun deleteAll()
}