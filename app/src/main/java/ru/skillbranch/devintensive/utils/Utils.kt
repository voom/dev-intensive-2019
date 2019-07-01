package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val split = fullName?.split(' ')
        val first = split?.getOrNull(0).takeIf { !it.isNullOrBlank()}
        val last = split?.getOrNull(1).takeIf { !it.isNullOrBlank()}
        return Pair(first, last)
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return payload
            .replace(" ", divider)
            .split("")
            .map { s -> transliterate(s) }
            .joinToString("")
    }

    private fun transliterate(s: String): String {
        return when (s) {
            "а" -> "a"
            "А" -> "A"
            "б" -> "b"
            "Б" -> "B"
            "в" -> "v"
            "В" -> "V"
            "г" -> "g"
            "Г" -> "G"
            "д" -> "d"
            "Д" -> "D"
            "е" -> "e"
            "Е" -> "E"
            "ё" -> "e"
            "Ё" -> "E"
            "ж" -> "zh"
            "Ж" -> "Zh"
            "з" -> "z"
            "З" -> "Z"
            "и" -> "i"
            "И" -> "I"
            "й" -> "i"
            "Й" -> "I"
            "к" -> "k"
            "К" -> "K"
            "л" -> "l"
            "Л" -> "L"
            "м" -> "m"
            "М" -> "M"
            "н" -> "n"
            "Н" -> "N"
            "о" -> "o"
            "О" -> "O"
            "п" -> "p"
            "П" -> "P"
            "р" -> "r"
            "Р" -> "R"
            "с" -> "s"
            "С" -> "S"
            "т" -> "t"
            "Т" -> "T"
            "у" -> "u"
            "У" -> "U"
            "ф" -> "f"
            "Ф" -> "F"
            "х" -> "h"
            "Х" -> "H"
            "ц" -> "c"
            "Ц" -> "C"
            "ч" -> "ch"
            "Ч" -> "Ch"
            "ш" -> "sh"
            "Ш" -> "Sh"
            "щ" -> "sh'"
            "Щ" -> "Sh'"
            "ъ" -> ""
            "ы" -> "i"
            "Ы" -> "I"
            "ь" -> ""
            "э" -> "e"
            "Э" -> "E"
            "ю" -> "yu"
            "Ю" -> "Yu"
            "я" -> "ya"
            "Я" -> "Ya"
            else -> s
        }
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