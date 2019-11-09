package com.shivam.devfestblr.model

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shivam.devfestblr.MyApplication
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Favorites(
    @PrimaryKey
    var id: String = "",
    var time: Long = Date().time
) {
    companion object {
        var url = MyApplication.firebaseDatabase
            .collection("favorites")
            .document(MyApplication.instance.mAuth.currentUser!!.uid)
            .collection("likes")

        private fun insertAll(myList: ArrayList<Favorites>) =
            MyApplication.database.favouriteDao().insert(myList)

        private fun insert(item: Favorites) = MyApplication.database.favouriteDao().insert(item)

        fun isLiked(id: String): List<Favorites> = MyApplication.database.favouriteDao().isLiked(id)

        var updateListener: LiveData<List<Favorites>> =
            MyApplication.database.favouriteDao().getList()

        private var deleteAll = MyApplication.database.favouriteDao().deleteAll()

        private fun delete(id: String) {
            MyApplication.database.favouriteDao().delete(id)
        }

        fun sync() {
            url
                .get()
                .addOnSuccessListener {
                    val list = ArrayList<Favorites>()
                    it?.forEach {
                        val obj = it.toObject(Favorites::class.java)
                        list.add(obj)
                    }
                    deleteAll
                    insertAll(list)
                }
        }

        fun doLike(id: String, isLiked: Boolean) {
            if (isLiked) {
                url.document(id).delete()
                delete(id)
            } else {
                val favorites = Favorites(id)
                insert(favorites)
                url.document(id).set(favorites)
            }
        }
    }
}