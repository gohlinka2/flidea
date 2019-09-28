package cz.hlinkapp.flidea.model

/**
 * A model class representing the search filters.
 */
data class SearchFilter(
    var passengers : Int,
    var airport: String
) {
}