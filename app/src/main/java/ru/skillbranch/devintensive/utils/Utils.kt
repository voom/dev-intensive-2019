package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val split = fullName?.split(' ')
        val first = split?.getOrNull(0).takeIf { !it.isNullOrBlank()}
        val last = split?.getOrNull(1).takeIf { !it.isNullOrBlank()}
        return Pair(first, last)
    }
}