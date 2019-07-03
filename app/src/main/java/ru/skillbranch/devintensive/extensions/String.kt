package ru.skillbranch.devintensive.extensions

const val DEFAULT_LENGTH = 16
const val ELLIPSIS = "..."

fun String.truncate(length: Int = DEFAULT_LENGTH): String {
    var text = this.trimEnd()
    if (text.length > length) text = text.substring(0, length).trimEnd().plus(ELLIPSIS)
    return text
}