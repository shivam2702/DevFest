package com.shivam.devfestblr.model

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shivam.devfestblr.MyApplication

@Entity
data class Sponsor(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var title: String = "",
    var imageUrl: String = "",
    var description: String = "",
    var email: String? = null,
    var phone: String? = null,
    var facebook: String? = null,
    var twitter: String? = null,
    var website: String? = null
) {
    var getImageFullUrl: String =
        "https://firebasestorage.googleapis.com/v0/b/devfest-49f5d.appspot.com/o/sponsor%2F$imageUrl?alt=media"

    fun insertToCloud() {
        url.document(id).set(this)
        insert()
    }

    fun insert() {
        MyApplication.database.sponsorDao().insert(this)
    }

    companion object {
        var url = MyApplication.firebaseDatabase.collection("sponsors")

        private fun insertAll(myList: ArrayList<Sponsor>) {
            MyApplication.database.sponsorDao().insert(myList)
        }

        private fun deleteAll() {
            MyApplication.database.sponsorDao().deleteAll()
        }

        fun sync() {
            url
                .get()
                .addOnSuccessListener {
                    val list = ArrayList<Sponsor>()
                    it.forEach {
                        val obj = it.toObject(Sponsor::class.java)
                        list.add(obj)
                    }
                    deleteAll()
                    insertAll(list)
                }
        }

        fun getList(): LiveData<List<Sponsor>> {
            return MyApplication.database.sponsorDao().getList()
        }

        fun getInfo(id: String): LiveData<Sponsor> {
            return MyApplication.database.sponsorDao().getInfo(id)
        }
    }
}