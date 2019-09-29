package cz.hlinkapp.flidea.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

        searchView.setOnQueryTextListener( object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }
}
