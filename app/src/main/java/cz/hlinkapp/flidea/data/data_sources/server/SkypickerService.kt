package cz.hlinkapp.flidea.data.data_sources.server

import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.model.LocationSearchRootApiResponse
import cz.hlinkapp.flidea.model.RootApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * A Retrofit interface for Kiwi.com skypicker api.
 */
interface SkypickerService {

    /**
     * Get a list of flights based on the provided search [params].
     */
    @GET(ServerContract.FLIGHTS)
    fun getFlights(@QueryMap params: @JvmSuppressWildcards Map<String,Any>) : Call<RootApiResponse>

    /**
     * Search locations including the query provided.
     */
    @GET(ServerContract.LOCATIONS)
    fun getAirportSearchResults(@QueryMap params: @JvmSuppressWildcards Map<String, Any>) : Call<LocationSearchRootApiResponse>
}