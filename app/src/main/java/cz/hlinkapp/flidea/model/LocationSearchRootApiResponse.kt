package cz.hlinkapp.flidea.model

/**
 * A model class representing the root of the response to a request for airport search results.
 */
data class LocationSearchRootApiResponse (
    var locations: List<Airport>
)