package cz.hlinkapp.flidea.utils

/**
 * An interface Fragments can implement to listen for click events of parent Activity's FloatingActionButton.
 */
interface OnFabClickedListener {
    /**
     * Called when the fab was clicked.
     */
    fun onFabClicked()
}