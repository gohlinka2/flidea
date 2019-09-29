package cz.hlinkapp.flidea.activities

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.adapters.MainViewPagerAdapter
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.fragments.SearchFilterFragment
import cz.hlinkapp.flidea.model.SearchFilters
import cz.hlinkapp.flidea.utils.SharedPrefHelper
import cz.hlinkapp.flidea.utils.findBehavior
import cz.hlinkapp.flidea.utils.format
import cz.hlinkapp.flidea.utils.getStartOfDayTimestamp
import cz.hlinkapp.flidea.view_models.MainViewModel
import cz.hlinkapp.gohlinka2_utils2.utils.OnChildScrollListener
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import cz.hlinkapp.gohlinka2_utils2.utils.setGone
import cz.hlinkapp.gohlinka2_utils2.utils.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import ru.semper_viventem.backdrop.BackdropBehavior
import java.util.*
import javax.inject.Inject

/**
 * The apps main activity, containing the ViewPager with the flight ideas.
 */
class MainActivity : AppCompatActivity(), OnChildScrollListener{

    override fun onScrollUp() {
        fab?.show()
    }

    override fun onScrollDown() {
        fab?.hide()
    }

    private val mAdapter = lazy {
        MainViewPagerAdapter(supportFragmentManager)
    }

    private lateinit var viewModel : MainViewModel

    private var mDeepLinks : ArrayList<String?> = ArrayList<String?>().apply {
        clear()
        for (i in 0 .. 4) this.add(null)
    }

    private var mSearchFilters : SearchFilters? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mSharedPrefHelper: SharedPrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //Inject and init ViewModel
        (applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.initFlights()

        initViews()
    }

    /**
     * Initializes the views of this activity.
     */
    private fun initViews() {
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)

        initSearchFilters()
        initBackdropViews()
        initContentViews()
    }

    /**
     * Init views related to the front layer.
     */
    private fun initContentViews() {
        viewPager.adapter = mAdapter.value
        tabLayout.setupWithViewPager(viewPager)
        fab.setOnClickListener {
            val deepLink = mDeepLinks[viewPager.currentItem]
            if (deepLink != null) startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)))
        }

        //Observe flights to hide the footer or show it with the outdated data message
        viewModel.flights?.observe(this, Observer {
            if (it.isNotEmpty()) {
                fab.show()
                val timestamp = it[0].display_day_timestamp
                if (timestamp == getStartOfDayTimestamp()) {
                    footerLayout.setGone()
                } else {
                    footerLayout.setVisible()
                    val cal = Calendar.getInstance()
                    cal.timeInMillis = timestamp
                    footerTitle.text = getString(R.string.todays_flight_ideas_havent_been_downloaded_yet_showing_flideas_for,"${cal.get(Calendar.DAY_OF_MONTH).format(2)}/${(cal.get(Calendar.MONTH) + 1).format(2)}")
                }
                for (i in 0 .. 4) mDeepLinks[i] = it[i].deep_link
            } else {
                fab.hide()
                footerLayout.setVisible()
                footerTitle.text = getString(R.string.data_not_saved)
            }
        })

        //Observe status to let the user know about what is happening
        viewModel.flightsStatus.observe(this, Observer {requestInfo ->
            if (requestInfo.isProcessing()) {
                footerLayout.setVisible()
                status?.text = getString(R.string.downloading)
            } else requestInfo.requestResult.getContentIfNotHandled().let {
                when (it) {
                    RequestInfo.RequestResult.OK -> {
                        footerLayout.setGone()
                        status?.text = getString(R.string.download_ok)
                    }
                    RequestInfo.RequestResult.FAILED -> {
                        footerLayout.setVisible()
                        status?.text = getString(R.string.download_failed)
                    }
                    RequestInfo.RequestResult.NO_INTERNET -> {
                        footerLayout.setVisible()
                        status?.text = getString(R.string.no_internet)
                    }
                    else -> {
                        footerLayout.setGone()
                        status?.text = getString(R.string.idle)
                    }
                }
            }
        })
    }

    /**
     * Init view related ton the backdrop itself or its back layer.
     */
    private fun initBackdropViews() {
        frontLayerScrim.setGone()

        val backdropBehavior: BackdropBehavior = foregroundContainer.findBehavior() // find behavior
        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)
            setClosedIcon(R.drawable.ic_menu)
            setOpenedIcon(R.drawable.ic_close)
            addOnDropListener(listener = object: BackdropBehavior.OnDropListener {
                override fun onDrop(dropState: BackdropBehavior.DropState, fromUser: Boolean) {
                    when (dropState) {
                        BackdropBehavior.DropState.OPEN -> frontLayerScrim.setVisible()
                        BackdropBehavior.DropState.CLOSE -> {
                            frontLayerScrim.setGone()
                            val newSearchFilters = mSharedPrefHelper.getSearchFilters()
                            if(newSearchFilters != mSearchFilters) viewModel.invalidateData(Handler())
                            mSearchFilters = newSearchFilters
                        }
                    }
                }
            })
        }

        frontLayerScrim.setOnClickListener {
            backdropBehavior.close(true)
            frontLayerScrim.setGone()
        }

        supportFragmentManager.commit {
            replace(searchFilterFragmentContainer.id, SearchFilterFragment(), SearchFilterFragment.TAG)
        }
    }

    private fun initSearchFilters() {
        mSearchFilters = mSharedPrefHelper.getSearchFilters()
    }
}
