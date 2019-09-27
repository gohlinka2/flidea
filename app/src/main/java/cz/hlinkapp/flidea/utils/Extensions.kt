package cz.hlinkapp.flidea.utils

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Resets this Calendar to the first millisecond of the day set.
 */
fun Calendar.resetToStartOfDay() {
    set(Calendar.HOUR_OF_DAY,0)
    set(Calendar.MINUTE,0)
    set(Calendar.SECOND,0)
    set(Calendar.MILLISECOND,0)
}

/**
 * Returns a timestamp of the start of the current day.
 */
fun getStartOfDayTimestamp() : Long {
    val cal = Calendar.getInstance()
    cal.resetToStartOfDay()
    return cal.timeInMillis
}

/**
 * Returns a [CoordinatorLayout] behavior of this view.
 * From https://github.com/Semper-Viventem/Material-backdrop/blob/master/README.md
 */
fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    require(this is CoordinatorLayout.LayoutParams) { "View's layout params should be CoordinatorLayout.LayoutParams" }
    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}

/**
 * Formats this integer as a String with the given number of [digits] and with a leading zero.
 */
fun Int.format(digits: Int): String = java.lang.String.format("%0${digits}d", this)

/**
 * Converts the [json] string to [T].
 */
inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)