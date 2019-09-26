package cz.hlinkapp.flidea.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.di.FlideaApplication
import cz.hlinkapp.flidea.fragments.FlideaFragment
import cz.hlinkapp.flidea.utils.findBehavior
import cz.hlinkapp.flidea.utils.format
import cz.hlinkapp.flidea.utils.getStartOfDayTimestamp
import cz.hlinkapp.flidea.view_models.MainViewModel
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import cz.hlinkapp.gohlinka2_utils2.utils.setGone
import cz.hlinkapp.gohlinka2_utils2.utils.setVisible
import kotlinx.android.synthetic.main.activity_main.*
import ru.semper_viventem.backdrop.BackdropBehavior
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    /*private val mAdapter = lazy {
        MainViewPagerAdapter(supportFragmentManager)
    }*/

    private lateinit var viewModel : MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.initFlights()

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)
        val tl = toolbar

        val backdropBehavior: BackdropBehavior = foregroundContainer.findBehavior() // find behavior

        with(backdropBehavior) {
            attachBackLayout(R.id.backLayout)

            setClosedIcon(R.drawable.ic_menu)
            setOpenedIcon(R.drawable.ic_close)

            addOnDropListener(object : BackdropBehavior.OnDropListener {
                override fun onDrop(dropState: BackdropBehavior.DropState, fromUser: Boolean) {
                    // TODO: handle listener
                }
            })
        }

        viewModel.flights?.observe(this, Observer {
            if (it.isNotEmpty()) {
                val timestamp = it[0].display_day_timestamp
                if (timestamp == getStartOfDayTimestamp()) {
                    footerLayout.setGone()
                } else {
                    footerLayout.setVisible()
                    val cal = Calendar.getInstance()
                    cal.timeInMillis = timestamp
                    footerTitle.text = getString(R.string.todays_flight_ideas_havent_been_downloaded_yet_showing_flideas_for,"${cal.get(Calendar.DAY_OF_MONTH).format(2)}/${(cal.get(Calendar.MONTH) + 1).format(2)}")
                }
            } else {
                footerLayout.setVisible()
                footerTitle.text = getString(R.string.data_not_saved)
            }
        })

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

//        viewPager.adapter = mAdapter.value

        supportFragmentManager.commit {
            replace(fragmentContainer.id,FlideaFragment.createInstance(0))
        }

    }
}
