package cz.hlinkapp.flidea.utils

import java.util.*

fun Calendar.resetToStartOfDay() {
    set(Calendar.HOUR_OF_DAY,0)
    set(Calendar.MINUTE,0)
    set(Calendar.SECOND,0)
    set(Calendar.MILLISECOND,0)
}

fun getStartOfDayTimestamp() : Long {
    val cal = Calendar.getInstance()
    cal.resetToStartOfDay()
    return cal.timeInMillis
}