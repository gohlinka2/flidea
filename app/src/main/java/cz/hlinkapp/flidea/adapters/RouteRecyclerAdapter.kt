package cz.hlinkapp.flidea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.model.Route
import cz.hlinkapp.flidea.utils.format
import kotlinx.android.synthetic.main.item_route.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A [RecyclerView] adapter for displaying single flights.
 */
class RouteRecyclerAdapter : RecyclerView.Adapter<RouteRecyclerAdapter.ViewHolder>() {

    var routes = ArrayList<Route>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_route,parent,false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int = routes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder) {
            val route = routes[position]
            mRoot.departure_airport.text = "${route.cityFrom} ${route.flyFrom}"
            mRoot.arrival_airport.text = "${route.cityTo} ${route.flyTo}"
            val dCal = Calendar.getInstance().apply { timeInMillis = route.dTime * 1000}
            val aCal = Calendar.getInstance().apply { timeInMillis = route.aTime * 1000}
            mRoot.departure_time.text = "${dCal.get(Calendar.DAY_OF_MONTH).format(2)}/${(dCal.get(Calendar.MONTH) + 1).format(2)}/${dCal.get(Calendar.YEAR)} ${dCal.get(Calendar.HOUR_OF_DAY).format(2)}:${dCal.get(Calendar.MINUTE).format(2)}"
            mRoot.arrival_time.text = "${aCal.get(Calendar.DAY_OF_MONTH).format(2)}/${(aCal.get(Calendar.MONTH) + 1).format(2)}/${aCal.get(Calendar.YEAR)} ${aCal.get(Calendar.HOUR_OF_DAY).format(2)}:${aCal.get(Calendar.MINUTE).format(2)}"
            val flightTime = route.aTimeUTC - route.dTimeUTC
            val flightHours = flightTime / 3600
            val flightMinutes = (flightTime / 60) % 60
            mRoot.fly_time.text = mRoot.context.getString(R.string.fly_time,flightHours,flightMinutes)
            Glide.with(mRoot.airline_logo)
                .load(ServerContract.createAirlineLogoImageUrl(route.airline))
                .downsample(DownsampleStrategy.FIT_CENTER)
                //TODO: add placeholder and error resources
                .into(mRoot.airline_logo)
            mRoot.airline.text = route.airline //TODO: for now. Replace with airline name from airlines api.
        }
    }

    inner class ViewHolder(val mRoot: View) : RecyclerView.ViewHolder(mRoot)

}