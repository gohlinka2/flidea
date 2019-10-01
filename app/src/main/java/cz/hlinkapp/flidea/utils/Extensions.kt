package cz.hlinkapp.flidea.utils

/**
 * Formats this integer as a String with the given number of [digits] and with a leading zero.
 */
fun Int.format(digits: Int): String = java.lang.String.format("%0${digits}d", this)