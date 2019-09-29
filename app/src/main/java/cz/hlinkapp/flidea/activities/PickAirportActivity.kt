package cz.hlinkapp.flidea.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.hlinkapp.flidea.R
import kotlinx.android.synthetic.main.activity_pick_airport.*

class PickAirportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_airport)

        initViews()
    }

    private fun initViews() {

        searchView.isSubmitButtonEnabled = true
        searchView.setIconifiedByDefault(false)
    }
}
