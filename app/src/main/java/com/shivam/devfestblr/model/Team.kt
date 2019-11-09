package com.shivam.devfestblr.model

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shivam.devfestblr.MyApplication
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Team(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var title: String = "",
    var company: String = "",
    var imageUrl: String = "",
    var bio: String = "",
    var socialList: List<Social> = ArrayList()
) {
    var getImageFullUrl: String =
        "https://firebasestorage.googleapis.com/v0/b/devfest-49f5d.appspot.com/o/team%2F$imageUrl?alt=media"

    fun insertToCloud() {
        url.document(id).set(this)
        insert()
    }

    fun insert() {
        MyApplication.database.teamDao().insert(this)
    }

    companion object {
        var url = MyApplication.firebaseDatabase.collection("teams")

        private fun insertAll(myList: ArrayList<Team>) {
            MyApplication.database.teamDao().insert(myList)
        }

        private fun deleteAll() {
            MyApplication.database.teamDao().deleteAll()
        }

        fun sync() {
            url
                .get()
                .addOnSuccessListener {
                    val list = ArrayList<Team>()
                    it.forEach {
                        val obj = it.toObject(Team::class.java)
                        list.add(obj)
                    }
                    deleteAll()
                    insertAll(list)
                }
        }

        fun getList(): LiveData<List<Team>> {
            return MyApplication.database.teamDao().getList()
        }

        fun getInfo(id: String): LiveData<Team> {
            return MyApplication.database.teamDao().getInfo(id)
        }

        fun createTeam() {
            val socialList = ArrayList<Social>()
            socialList.add(SocialType.createLinkedInProfile("https://www.linkedin.com/in/shivam-android"))
            socialList.add(SocialType.createTwitterProfile("https://twitter.com/Mathur2Shivam"))
            socialList.add(SocialType.createPhoneProfile("+917023271505"))
            socialList.add(SocialType.createGitHubProfile("https://github.com/shivam2702"))
            socialList.add(SocialType.createFacebookProfile("https://facebook.com/shivam2702"))
            socialList.add(SocialType.createEmailProfile("shivam.mathur10@gmail.com"))
            socialList.add(SocialType.createDribbleProfile("https://dribbble.com/k2sper"))
            socialList.add(SocialType.createBehanceProfile("https://www.behance.net/terricolaespino"))
            val team = Team(
                UUID.randomUUID().toString(),
                "Shivam Mathur",
                "Android Software Developer",
                "Philips India",
                "https://firebasestorage.googleapis.com/v0/b/devfest-49f5d.appspot.com/o/team%2Fshivam.jpg?alt=media",
                "I am Shivam Mathur with having work experience of 3+ years. I am working in Philips Healthcare in Personal Health department. Before Philips I was working in a start up.",
                socialList
            )
            team.insertToCloud()

        }
    }
}