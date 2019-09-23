package cz.hlinkapp.flidea.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.view_models.MainViewModel
import cz.hlinkapp.gohlinka2_utils2.fragments.abstraction.BaseFragment
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_flidea.*
import javax.inject.Inject

class FlideaFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_flidea

    private lateinit var viewModel : MainViewModel
    private var mIndex : Int = -1

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGson : Gson //TODO: remove, just for testing

    override fun initDependencies(savedInstanceState: Bundle?) {
        super.initDependencies(savedInstanceState)

        val fromSavedInstanceState = savedInstanceState?.getInt(ARG_FRAG_INDEX,-1) ?: -1
        val fromExtras = activity?.intent?.getIntExtra(ARG_FRAG_INDEX,-1) ?: -1
        mIndex = if (fromExtras != -1) fromExtras else if (fromSavedInstanceState != 1) fromSavedInstanceState else throw IllegalArgumentException("No index provided with ARG_FRAG_INDEX!")

        (activity?.applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.initFlights()
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        viewModel.flightsStatus.observe(viewLifecycleOwner, Observer {requestInfo ->
            if (requestInfo.isProcessing()) status.text = "Downloading"
            else requestInfo.requestResult.getContentIfNotHandled().let {
                when (it) {
                    RequestInfo.RequestResult.OK -> {
                        status.text = "OK"
                        viewPager.currentItem = 0
                    }
                    RequestInfo.RequestResult.FAILED -> status.text = "Failed"
                    RequestInfo.RequestResult.NO_INTERNET -> status.text = "No internet"
                    else -> status.text = "Idle"
                }
            }
        })

        viewModel.flights?.observe(viewLifecycleOwner, Observer {
            val flight = it[mIndex]
            destination.text = flight.cityTo
            price.text = "${flight.price} ${flight.currency}"
            dump.text = mGson.toJson(flight).toString()
        })
    }

    companion object {
        const val ARG_FRAG_INDEX = "fragmentIndex"
        const val TAG = "FlideaFragment"

        fun createInstance(fragIndex: Int) : FlideaFragment {
            val frag = FlideaFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_FRAG_INDEX,fragIndex)
            frag.arguments = bundle
            return frag
        }
    }
}