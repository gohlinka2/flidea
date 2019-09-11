package cz.hlinkapp.flidea.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.hlinkapp.flidea.R
import io.github.rokarpov.backdrop.BackdropController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var backdropController: BackdropController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }
}
