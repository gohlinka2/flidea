package cz.hlinkapp.flidea.data.data_sources.server

import RootApiResponse
import cz.hlinkapp.flidea.contracts.ServerContract
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SkypickerService {

    @GET(ServerContract.FLIGHTS)
    fun getFlights(@QueryMap params: Map<String,Any>) : Call<RootApiResponse>
}