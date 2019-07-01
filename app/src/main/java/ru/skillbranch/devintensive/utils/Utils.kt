package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val split = fullName?.split(' ')
        val first = split?.getOrNull(0).takeIf { !it.isNullOrBlank()}
        val last = split?.getOrNull(1).takeIf { !it.isNullOrBlank()}
        return Pair(first, last)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstLetter: String? = if (firstName.isNullOrBlank()) null else firstName.substring(0, 1).toUpperCase()
        val lastLetter: String? = if (lastName.isNullOrBlank()) null else lastName.substring(0, 1).toUpperCase()
        return when {
            firstLetter == null && lastLetter == null -> null
            firstLetter == null -> lastLetter
            lastLetter == null -> firstLetter
            else -> firstLetter + lastLetter
        }
    }
}