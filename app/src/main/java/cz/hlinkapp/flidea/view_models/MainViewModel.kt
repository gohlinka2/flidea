package cz.hlinkapp.flidea.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.hlinkapp.flidea.data.repositories.MainRepository
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: MainRepository): ViewModel() {

    private val mMainRepository : MainRepository = repository

    private var mFlights : LiveData<List<Flight>>? = null

    val flights : LiveData<List<Flight>>? get() = mFlights

    val flightsStatus : LiveData<RequestInfo> get() = mMainRepository.flightsStatus


    fun initFlights() {
        if (mFlights == null) mFlights = mMainRepository.getFlights()
    }

    /*fun forceRefreshFlights() {
        mFlights = mMainRepository.forceRefreshFlights()
    }*/
}