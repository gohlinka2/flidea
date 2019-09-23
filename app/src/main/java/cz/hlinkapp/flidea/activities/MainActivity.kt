package cz.hlinkapp.flidea.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.adapters.MainViewPagerAdapter
import cz.hlinkapp.flidea.di.FlideaApplication
import io.github.rokarpov.backdrop.BackdropController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backdropController: BackdropController? = null

    private val mAdapter = lazy {
        MainViewPagerAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as? FlideaApplication)?.getApplicationComponent()?.inject(this)

        initViews()
    }

    private fun initViews() {
        setSupportActionBar(toolbar)
        toolbar.title = getString(R.string.app_name)
        val tl = toolbar

        val backLayer = backdropBackLayer
        backdropController = BackdropController.build(backLayer, applicationContext) {
            supportToolbar = tl
            concealedTitleId = R.string.app_name
            concealedNavigationIconId = R.drawable.ic_menu
            revealedNavigationIconId = R.drawable.ic_close
        }

        viewPager.adapter = mAdapter.value

    }
}
