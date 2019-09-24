package cz.hlinkapp.flidea.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.google.gson.Gson
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.view_models.MainViewModel
import cz.hlinkapp.gohlinka2_utils2.fragments.abstraction.BaseFragment
import cz.hlinkapp.gohlinka2_utils2.utils.setGone
import cz.hlinkapp.gohlinka2_utils2.utils.setVisible
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
        val fromExtras = arguments?.getInt(ARG_FRAG_INDEX,-1) ?: -1
        mIndex = if (fromExtras != -1) fromExtras else if (fromSavedInstanceState != -1) fromSavedInstanceState else throw IllegalArgumentException("No index provided with ARG_FRAG_INDEX!")

        (activity?.applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.initFlights()
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        emptySpaceLayout.setOnClickListener {
            //TODO: force refresh data
        }

        viewModel.flights?.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                contentLayout.setVisible()
                emptySpaceLayout.setGone()
                val flight = it[mIndex]
                destination.text = flight.cityTo
                country.text = flight.countryTo.name
                airport.text = flight.flyTo
                price.text = "${flight.price} ${flight.currency}"
                Glide.with(destinationImage)
                    .load(ServerContract.createImageUrl(flight.mapIdto))
                    .downsample(DownsampleStrategy.FIT_CENTER)
                    .into(destinationImage)
                dump.text = mGson.toJson(flight).toString()
            } else {
                contentLayout.setGone()
                emptySpaceLayout.setVisible()
            }
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