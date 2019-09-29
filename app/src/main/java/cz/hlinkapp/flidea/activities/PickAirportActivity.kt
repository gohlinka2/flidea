package cz.hlinkapp.flidea.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.adapters.AirportSearchResultRecyclerAdapter
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.view_models.LocationSearchViewModel
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import cz.hlinkapp.gohlinka2_utils2.utils.setGone
import cz.hlinkapp.gohlinka2_utils2.utils.setLayoutManagerSafely
import cz.hlinkapp.gohlinka2_utils2.utils.setVisible
import kotlinx.android.synthetic.main.activity_pick_airport.*
import javax.inject.Inject

class PickAirportActivity : AppCompatActivity() {

    private lateinit var viewModel : LocationSearchViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var mAirportSearchResultRecyclerAdapter = AirportSearchResultRecyclerAdapter {
        val data = Intent()
        data.putExtra(KEY_AIRPORT_NAME,it.name)
        data.putExtra(KEY_AIRPORT_ID,it.id)
        setResult(Activity.RESULT_OK,data)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_airport)

        //Inject and init ViewModel
        (applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LocationSearchViewModel::class.java)

        initViews()
    }

    private fun initViews() {

        resultsRecycler.setLayoutManagerSafely(LinearLayoutManager(this))
        resultsRecycler.adapter = mAirportSearchResultRecyclerAdapter

        searchView.isSubmitButtonEnabled = true
        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchAirports(query)
                return true
            }
            override fun onQueryTextChange(newText: String): Boolean = false
        })

        viewModel.airportResults.observe(this, Observer {
            when {
                it != null -> {
                    progressBar.setGone()
                    errorLayout.setGone()
                    resultsRecycler.setGone()
                }
                it?.isEmpty() != false -> {
                    progressBar.setGone()
                    errorLayout.setVisible()
                    resultsRecycler.setGone()
                    errorReason.text = getString(R.string.nothing_found)
                }
                else -> {
                    progressBar.setGone()
                    errorLayout.setGone()
                    resultsRecycler.setVisible()
                    mAirportSearchResultRecyclerAdapter.airports = it
                }
            }

        })

        viewModel.airportResultsStatus.observe(this, Observer { requestInfo ->
            if (requestInfo.isProcessing()) {
                progressBar.setVisible()
                errorLayout.setGone()
                resultsRecycler.setGone()
            } else requestInfo.requestResult.getContentIfNotHandled().let {
                when (it) {
                    RequestInfo.RequestResult.OK -> {
                        progressBar.setGone()
                        errorLayout.setGone()
                        resultsRecycler.setVisible()
                    }
                    RequestInfo.RequestResult.FAILED -> {
                        progressBar.setGone()
                        errorLayout.setVisible()
                        resultsRecycler.setGone()
                        errorReason.text = getString(R.string.download_failed)
                    }
                    RequestInfo.RequestResult.NO_INTERNET -> {
                        progressBar.setGone()
                        errorLayout.setVisible()
                        resultsRecycler.setGone()
                        errorReason.text = getString(R.string.no_internet)
                    }
                    else -> {
                        progressBar.setGone()
                        errorLayout.setGone()
                        resultsRecycler.setGone()
                    }
                }
            }
        })

    }

    companion object {
        const val KEY_AIRPORT_NAME = "airportName"
        const val KEY_AIRPORT_ID = "airportId"
    }
}
