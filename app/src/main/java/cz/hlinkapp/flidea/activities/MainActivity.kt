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
import cz.hlinkapp.flidea.view_models.MainViewModel
import cz.hlinkapp.gohlinka2_utils2.utils.RequestInfo
import kotlinx.android.synthetic.main.activity_main.*
import ru.semper_viventem.backdrop.BackdropBehavior
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

        viewModel.flightsStatus.observe(this, Observer {requestInfo ->
            if (requestInfo.isProcessing()) status?.text = "Downloading"
            else requestInfo.requestResult.getContentIfNotHandled().let {
                when (it) {
                    RequestInfo.RequestResult.OK -> {
                        status?.text = "OK"
//                        viewPager.currentItem = 0
                    }
                    RequestInfo.RequestResult.FAILED -> status?.text = "Failed"
                    RequestInfo.RequestResult.NO_INTERNET -> status?.text = "No internet"
                    else -> status?.text = "Idle"
                }
            }
        })

//        viewPager.adapter = mAdapter.value

        supportFragmentManager.commit {
            replace(fragmentContainer.id,FlideaFragment.createInstance(0))
        }

    }
}
