package com.shivam.devfestblr.model

import androidx.lifecycle.LiveData
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shivam.devfestblr.MyApplication
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Speaker(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var title: String = "",
    var company: String = "",
    var imageUrl: String = "",
    var bio: String = "",
    var socialList: List<Social> = ArrayList(),
    var sessionId: List<String> = ArrayList()
) {
    fun insertToCloud() {
        url.document(id).set(this)
        insert()
    }

    fun insert() {
        MyApplication.database.speakerDao().insert(this)
    }

    fun toggleFav(isLiked: Boolean) {
        Favorites.doLike(id, isLiked)
    }

    companion object {
        var url = MyApplication.firebaseDatabase.collection("speakers")

        private fun insertAll(myList: ArrayList<Speaker>) {
            MyApplication.database.speakerDao().insert(myList)
        }

        private fun deleteAll() {
            MyApplication.database.speakerDao().deleteAll()
        }

        fun sync() {
            url
                .get()
                .addOnSuccessListener {
                    val list = ArrayList<Speaker>()
                    it.forEach {
                        val obj = it.toObject(Speaker::class.java)
                        list.add(obj)
                    }
                    deleteAll()
                    insertAll(list)
                }
        }

        fun getList(speakerId: List<String>): List<Speaker> {
            return MyApplication.database.speakerDao().getList(speakerId)
        }

        fun getInfo(speakerId: String): LiveData<Speaker> {
            return MyApplication.database.speakerDao().getInfo(speakerId)
        }

        fun getAll(): LiveData<List<Speaker>> {
            return MyApplication.database.speakerDao().getList()
        }

        fun createSpeaker() {
            val socialList = ArrayList<Social>()
            socialList.add(SocialType.createLinkedInProfile("https://www.linkedin.com/in/saumitri"))
            val speaker = Speaker(
                UUID.randomUUID().toString(),
                "Saumitri Choudhury",
                "General Manager",
                "Samsung",
                "https://devfest-blr.firebaseapp.com/images/speakers/saumitri.jpg",
                "Saumitri Choudhury, He works as General Manager at Samsung, He works at the intersection of Product and Service Design, Product Management, Technology and Marketing, bringing new value to consumers, and unlocking stakeholder value. With a career of 20+ years, has spanned all aspects of the business of Design-led Innovation such as • Product Leadership: • Senior Management : • Design Consultancy/Agency • Product & UX Designer • Design Research • Design Advocacy & Evangelization",
                socialList,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker.insertToCloud()

            val socialList1 = ArrayList<Social>()
            socialList1.add(SocialType.createGitHubProfile("https://github.com/amsanjeev"))
            socialList1.add(SocialType.createLinkedInProfile("https://www.linkedin.com/in/amrit-sanjeev-a4984438/?originalSubdomain=in"))
            socialList1.add(SocialType.createTwitterProfile("https://twitter.com/amsanjeev"))
            val speaker1 = Speaker(
                UUID.randomUUID().toString(),
                "Amrit Sanjeev",
                "Developer Advocate",
                "Google",
                "https://devfest-blr.firebaseapp.com/images/speakers/amrit.jpg",
                "Amrit is a Developer Advocate at Google in the partner developer relations team and works with top partners in India . Prior to joining Google he worked for companies including Intuit, Philips and IBM. He is one of the organizers of Blrdroid and is totally passionate about programming.",
                socialList1,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker1.insertToCloud()


            val socialList2 = ArrayList<Social>()
            socialList2.add(SocialType.createTwitterProfile("https://twitter.com/sachitmishradev"))
            val speaker2 = Speaker(
                UUID.randomUUID().toString(),
                "Sachit Mishra",
                "Developer Programs Engineer",
                "Google",
                "https://devfest-blr.firebaseapp.com/images/speakers/sanchit.jpg",
                "Sachit works on libraries, documentation, and outreach efforts for Actions on Google as a Developer Programs Engineer. He has also supported Android TV and Google Cast. Prior to Google, Sachit was a software engineer working at companies like Bloomberg, Intuit, and Grooveshark.Sachit earned a bachelor’s degree in Computer Science from the University of Florida.",
                socialList2,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker2.insertToCloud()

            val socialList3 = ArrayList<Social>()
            socialList3.add(SocialType.createTwitterProfile("https://twitter.com/k1shores"))
            val speaker3 = Speaker(
                UUID.randomUUID().toString(),
                "Kishore Subramanian",
                "Software Engineer",
                "Google",
                "https://devfest-blr.firebaseapp.com/images/speakers/kishore.jpg",
                "Kishore has 20+ years of experience building software products as part of startups and larger companies. In his current role, he is working to make it easier for developers to succeed with Actions on Google and Google Assistant. Prior to this, Kishore worked on Files Go (Android app), Google Web Designer, MontageJS Javascript framework and in Enterprise software.Kishore earned a bachelor's degree in Electronics Engineering from Kerala University.",
                socialList3,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker3.insertToCloud()


            val socialList4 = ArrayList<Social>()
            socialList4.add(SocialType.createTwitterProfile("https://twitter.com/vrushaliraut122"))
            val speaker4 = Speaker(
                UUID.randomUUID().toString(),
                "Vrushali Raut",
                "Product Engineer",
                "GO-Jek",
                "https://devfest-blr.firebaseapp.com/images/speakers/vrushali.jpg",
                "Vrushali is a Product Engineer, Android at Gojek. She is been working in industry around 3 years. Some of her hobbies are Gardening, Reading and Playing Foosball",
                socialList4,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker4.insertToCloud()


            val socialList5 = ArrayList<Social>()
            socialList5.add(SocialType.createTwitterProfile("https://twitter.com/aditlal"))
            val speaker5 = Speaker(
                UUID.randomUUID().toString(),
                "Adit Lal",
                "Product Engineer",
                "GO-Jek",
                "https://devfest-blr.firebaseapp.com/images/speakers/adit.jpg",
                "Adit is a Product Engineer, Android at GoJek. He is been working in the industry for close to 6 years. Some of his hobbies are Stargazing , Travel and landscape Photography",
                socialList5,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker5.insertToCloud()


            val socialList6 = ArrayList<Social>()
            socialList6.add(SocialType.createTwitterProfile("https://twitter.com/siriscac"))
            val speaker6 = Speaker(
                UUID.randomUUID().toString(),
                "Muthuramakrishnan",
                "Technical Solutions Consultant",
                "Google",
                "https://devfest-blr.firebaseapp.com/images/speakers/muthu.png",
                "Muthuramakrishnan has been working as a Technical Solutions Consultant at Google for about 2 years now and is currently focused on helping developers build efficient APIs and Microservices. He's an experienced full stack developer with expertise in building secure software that can scale. His passion for Open Source Technologies led him to publish several award winning Open Source libraries for Android. If you don't see him immersed in Software Development, he's most likely working the decks with deep house/trance mixes as a DJ.",
                socialList6,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker6.insertToCloud()


            val socialList7 = ArrayList<Social>()
            socialList7.add(SocialType.createTwitterProfile("https://twitter.com/bhatnagar_g"))
            val speaker7 = Speaker(
                UUID.randomUUID().toString(),
                "Gaurav Bhatnagar",
                "Mobile Developer",
                "Maersk",
                "https://devfest-blr.firebaseapp.com/images/speakers/gaurav.jpg",
                "Gaurav is a seasoned mobile developer currently working in Maersk(shipping conglomerate). He has worked in healthcare apps for more than five years targeting different spectrum of users right from Radiologists to Wardboys specifically for US & Canada. As an android enthusiast, he is fond of connecting with the community and spends time as a weekend trainer with Edureka for new entrants in mobile ecospace.",
                socialList7,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker7.insertToCloud()


            val socialList8 = ArrayList<Social>()
            socialList8.add(SocialType.createTwitterProfile("https://twitter.com/shrinathv"))
            val speaker8 = Speaker(
                UUID.randomUUID().toString(),
                "Shrinath V",
                "Product Consultant",
                "Google Developer Expert (Product Strategy)",
                "https://devfest-blr.firebaseapp.com/images/speakers/shrinath.jpg",
                "A Google Developer Expert (Product) . He brings a mix of executive and entrepreneurial experience in his work as a consultant/coach to startups and MNCs. He has worked closely on business strategy, product strategy, portfolio management, partnerships and led product & marketing teams across multiple sites and countries.",
                socialList8,
                listOf(
                    "c51c790a-f16e-4b86-9dad-a8008886b419",
                    "da596175-9c1e-4b91-a4a4-98e60051c44e"
                )
            )
            speaker8.insertToCloud()
        }
    }
}