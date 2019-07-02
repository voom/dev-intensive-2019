package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.math.sign

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    this.time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var diff = date.time - this.time
    val diffSign = diff.sign
    diff = abs(diff)

    val result: String = when {
        diff in (0 * SECOND)..(1 * SECOND) -> "только что"
        diff in (1 * SECOND)..(45 * SECOND) -> "несколько секунд"
        diff in (45 * SECOND)..(75 * SECOND) -> "минуту"
        diff in (75 * SECOND)..(45 * MINUTE) -> formatWithPlural(diff / MINUTE, TimeUnits.MINUTE)
        diff in (45 * MINUTE)..(75 * MINUTE) -> "час"
        diff in (75 * MINUTE)..(22 * HOUR) -> formatWithPlural(diff / HOUR, TimeUnits.HOUR)
        diff in (22 * HOUR)..(26 * HOUR) -> "день"
        diff in (26 * HOUR)..(360 * DAY) -> formatWithPlural(diff / DAY, TimeUnits.DAY)
        diff > (360 * DAY) -> "более года"
        else -> throw IllegalStateException("Invalid Time!")
    }

    return when (result) {
        "только что" -> result
        "более года" -> if (diffSign < 0) "более чем через год" else "$result назад"
        else -> if (diffSign < 0) "через $result" else "$result назад"
    }
}

private fun formatWithPlural(period: Long, unit: TimeUnits): String {
    val (one, few, many) = when (unit) {
        TimeUnits.SECOND -> Triple("секунду", "секунд", "секунды")
        TimeUnits.MINUTE -> Triple("минуту", "минут", "минуты")
        TimeUnits.HOUR -> Triple("час", "часов", "часа")
        TimeUnits.DAY -> Triple("день", "дней", "дня")
    }

    val rem = period.toInt().rem(10)

    return when (rem) {
        1 -> "$period $one"
        2, 3, 4 -> "$period $many"
        0, 5, 6, 7, 8, 9 -> "$period $few"
        else -> throw java.lang.IllegalStateException("Invalid period!")
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}