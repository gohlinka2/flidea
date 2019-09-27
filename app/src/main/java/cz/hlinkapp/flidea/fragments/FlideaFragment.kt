package cz.hlinkapp.flidea.fragments

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.adapters.RouteRecyclerAdapter
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.fragments.FlideaFragment.Companion.ARG_FRAG_INDEX
import cz.hlinkapp.flidea.model.Route
import cz.hlinkapp.flidea.view_models.MainViewModel
import cz.hlinkapp.gohlinka2_utils2.fragments.abstraction.BaseFragment
import cz.hlinkapp.gohlinka2_utils2.utils.setGone
import cz.hlinkapp.gohlinka2_utils2.utils.setLayoutManagerSafely
import cz.hlinkapp.gohlinka2_utils2.utils.setVisible
import kotlinx.android.synthetic.main.fragment_flidea.*
import javax.inject.Inject

/**
 * A Fragment for displaying a single flight idea with all of its flight details.
 * Designed to be displayed in a ViewPager with several instances of itself.
 * The fragment-index argument needs to be passed under the [ARG_FRAG_INDEX] key for this fragment to work.
 * Fragment-index is the index of this fragment in the ViewPager, so that it knows which flight idea to display.
 */
class FlideaFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_flidea

    private lateinit var viewModel : MainViewModel
    private var mIndex : Int = -1

    private var mRouteRecyclerAdapter = RouteRecyclerAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun initDependencies(savedInstanceState: Bundle?) {
        super.initDependencies(savedInstanceState)

        //init the fragment-index
        val fromSavedInstanceState = savedInstanceState?.getInt(ARG_FRAG_INDEX,-1) ?: -1
        val fromExtras = arguments?.getInt(ARG_FRAG_INDEX,-1) ?: -1
        mIndex = if (fromExtras != -1) fromExtras else if (fromSavedInstanceState != -1) fromSavedInstanceState else throw IllegalArgumentException("No index provided with ARG_FRAG_INDEX!")

        //Inject and init viewModel
        (activity?.applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.initFlights()
    }

    override fun initViews(savedInstanceState: Bundle?) {
        super.initViews(savedInstanceState)

        //Set up the route recycler
        routeRecycler.setLayoutManagerSafely(LinearLayoutManager(context))
        routeRecycler.adapter = mRouteRecyclerAdapter

        emptySpaceLayout.setOnClickListener {
            //TODO: force refresh data
        }

        //Observe the flights and display the flight idea
        viewModel.flights?.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                contentLayout.setVisible()
                emptySpaceLayout.setGone()
                val flight = it[mIndex]
                destination.text = flight.cityTo
                country.text = flight.countryTo.name
                airport.text = flight.flyTo
                price.text = flight.price.toString()
                currency.text = flight.currency
                Glide.with(destinationImage)
                    .load(ServerContract.createLocationImageUrl(flight.mapIdto))
                        //TODO: add placeholder and error resources
                    .downsample(DownsampleStrategy.FIT_CENTER)
                    .into(destinationImage)
                mRouteRecyclerAdapter.routes = ArrayList<Route>().apply { addAll(flight.route) }
            } else {
                contentLayout.setGone()
                emptySpaceLayout.setVisible()
            }
        })
    }

    companion object {
        /**
         * A [Bundle] key for the necessary fragment-index argument.
         * Fragment-index is the index of this fragment in the ViewPager, so that it knows which flight idea to display.
         */
        const val ARG_FRAG_INDEX = "fragmentIndex"
        const val TAG = "FlideaFragment"

        /**
         * Creates an instance of this fragment and passes the [fragIndex] as the required [ARG_FRAG_INDEX] extra/argument to it.
         * Use instead of the default constructor.
         */
        fun createInstance(fragIndex: Int) : FlideaFragment {
            val frag = FlideaFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_FRAG_INDEX,fragIndex)
            frag.arguments = bundle
            return frag
        }
    }
}