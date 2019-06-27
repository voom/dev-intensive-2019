package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(id, firstName, lastName, null)

    companion object Factory {
        private var lastId = -1
        fun makeUser(fullName: String?): User {
            val split = fullName?.split(' ')
            lastId++
            return User(id = "$lastId", firstName = split?.getOrNull(0), lastName = split?.getOrNull(1))
        }
    }

}