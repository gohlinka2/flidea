package cz.hlinkapp.flidea.model

/**
 * A model class representing the search filters.
 */
data class SearchFilters(
    var passengers : Int,
    var airportName: String,
    var airportCode: String
) {
}