package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

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
    val diff = date.time - this.time
    return when {
        diff in (0 * SECOND)..(1 * SECOND) -> "только что"
        diff in (1 * SECOND)..(45 * SECOND) -> "несколько секунд назад"
        diff in (45 * SECOND)..(75 * SECOND) -> "минуту назад"
        diff in (75 * SECOND)..(45 * MINUTE) -> formaDiff(diff / MINUTE, TimeUnits.MINUTE)
        diff in (45 * MINUTE)..(75 * MINUTE) -> "час назад"
        diff in (75 * MINUTE)..(22 * HOUR) -> formaDiff(diff / HOUR, TimeUnits.HOUR)
        diff in (22 * HOUR)..(26 * HOUR) -> "день назад"
        diff in (26 * HOUR)..(360 * DAY) -> formaDiff(diff / DAY, TimeUnits.DAY)
        diff > (360 * DAY) -> "более года назад"
        else -> throw IllegalStateException("Wrong Date!")
    }
}

private fun formaDiff(period: Long, unit: TimeUnits): String {
    val (unitForm1, unitForm2, unitForm3) = when (unit) {
        TimeUnits.SECOND -> Triple("секунд", "секунды", "секунду")
        TimeUnits.MINUTE -> Triple("минут", "минуты", "минуту")
        TimeUnits.HOUR -> Triple("часов", "часа", "час")
        TimeUnits.DAY -> Triple("дней", "дня", "день")
    }

    val rem = period.rem(10)

    return when {
        (rem in 5..19) || rem == 0L -> "$period $unitForm1 назад"
        rem == 1L -> "$period $unitForm3 назад"
        else -> "$period $unitForm2 назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}