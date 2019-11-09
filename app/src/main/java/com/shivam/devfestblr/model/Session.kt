package com.shivam.devfestblr.model

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shivam.devfestblr.MyApplication

@Entity
data class Session(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var startTime: Long = 0,
    var endTime: Long = 0,
    var location: String = "",
    var tags: List<String> = ArrayList(),
    var shortDescription: String = "",
    var longDescription: String = "",
    var sessionLevel: Float = 0f,
    var speakerId: List<String> = ArrayList(),
    var streamingLink: String = "",
    var favourite: Boolean = false
) {


    fun insert() {
        MyApplication.database.sessionDao().insert(this)
        url.document(id).set(this)
    }

    fun toggleFav(isLiked: Boolean) {
        favourite = !favourite
        Favorites.doLike(id, isLiked)
    }

    companion object {
        var url = MyApplication.firebaseDatabase.collection("sessions")

        private fun insertAll(myList: ArrayList<Session>) {
            MyApplication.database.sessionDao().insert(myList)
        }

        private fun deleteAll() {
            MyApplication.database.sessionDao().deleteAll()
        }

        fun getList(): LiveData<List<Session>> {
            return MyApplication.database.sessionDao().getList()
        }

        fun getDistinctLocation(): List<String> {
            return MyApplication.database.sessionDao().getDistinctLocation()
        }

        fun sync() {
            url
                .get()
                .addOnSuccessListener {
                    val list = ArrayList<Session>()
                    it?.forEach {
                        val obj = it.toObject(Session::class.java)
                        list.add(obj)
                    }
                    deleteAll()
                    insertAll(list)
                }
        }

        fun getSessionBySpeaker(id: List<String>): List<Session> {
            return MyApplication.database.sessionDao().getSessionBySpeaker(id)
        }

        fun getByLocation(filter: String): LiveData<List<Session>> {
            return MyApplication.database.sessionDao().getListByLocation(filter)
        }
    }
}