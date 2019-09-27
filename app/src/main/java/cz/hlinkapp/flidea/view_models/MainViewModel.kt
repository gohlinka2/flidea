package cz.hlinkapp.flidea.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.hlinkapp.flidea.data.repositories.MainRepository
import cz.hlinkapp.flidea.model.Flight
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import javax.inject.Inject

/**
 * The main ViewModel for accessing data.
 */
class MainViewModel @Inject constructor(repository: MainRepository): ViewModel() {

    private val mMainRepository : MainRepository = repository

    private var mFlights : LiveData<List<Flight>>? = null

    /**
     * The LiveData with flights/Flideas(flight ideas).
     */
    val flights : LiveData<List<Flight>>? get() = mFlights

    /**
     * An observable status of the flights download task.
     */
    val flightsStatus : LiveData<RequestInfo> get() = mMainRepository.flightsStatus


    /**
     * Call before accessing [flights].
     * Loads the flights.
     */
    fun initFlights() {
        if (mFlights == null) mFlights = mMainRepository.getFlights()
    }

    /**
     * Call to retry the flights download task, if the previous request has failed and the user wants to try again.
     */
    fun retryRefreshingFlights() {
        mMainRepository.retryRefreshingFlights()
    }
}