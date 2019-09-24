package cz.hlinkapp.flidea.utils

import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
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

/**
 * From https://github.com/Semper-Viventem/Material-backdrop/blob/master/README.md
 */
fun <T : CoordinatorLayout.Behavior<*>> View.findBehavior(): T = layoutParams.run {
    if (this !is CoordinatorLayout.LayoutParams) throw IllegalArgumentException("View's layout params should be CoordinatorLayout.LayoutParams")

    (layoutParams as CoordinatorLayout.LayoutParams).behavior as? T
        ?: throw IllegalArgumentException("Layout's behavior is not current behavior")
}