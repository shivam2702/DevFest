package com.shivam.devfestblr.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shivam.devfestblr.model.Session


@Dao
interface SessionDao {
    @Query("SELECT * from session order by startTime")
    fun getList(): LiveData<List<Session>>

    @Query("SELECT * from session WHERE id = :id LIMIT 1")
    fun getInfo(id: String): LiveData<Session>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(session: Session)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(session: ArrayList<Session>)

    @Query("DELETE from session")
    fun deleteAll()

    @Query("SELECT * from session WHERE id IN (:myList) ORDER BY startTime")
    fun getSessionBySpeaker(myList: List<String>): List<Session>

    @Query("SELECT DISTINCT(location) FROM session ORDER BY location")
    fun getDistinctLocation(): List<String>

    @Query("SELECT * from session WHERE location = :filter order by startTime")
    fun getListByLocation(filter: String): LiveData<List<Session>>
}