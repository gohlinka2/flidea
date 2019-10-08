package cz.hlinkapp.flidea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.contracts.ServerContract
import cz.hlinkapp.flidea.model.Route
import kotlinx.android.synthetic.main.item_route.view.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A [RecyclerView] adapter for displaying single flights of a journey.
 */
class RouteRecyclerAdapter : RecyclerView.Adapter<RouteRecyclerAdapter.ViewHolder>() {

    /**
     * The routes that should be displayed. 
     * The view will by automatically notified of dataSet changes when this field is updated.
     */
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
        with (holder.mRoot) {
            val route = routes[position]
            departure_airport.text = "${route.cityFrom} ${route.flyFrom}"
            arrival_airport.text = "${route.cityTo} ${route.flyTo}"

            val dCal = Calendar.getInstance().apply { timeInMillis = route.dTime * 1000}
            val aCal = Calendar.getInstance().apply { timeInMillis = route.aTime * 1000}
            departure_time.text = context.getString(R.string.date_time_formatted,dCal.get(Calendar.DAY_OF_MONTH),dCal.get(Calendar.MONTH) + 1,dCal.get(Calendar.YEAR),dCal.get(Calendar.HOUR_OF_DAY),dCal.get(Calendar.MINUTE))
            arrival_time.text = context.getString(R.string.date_time_formatted,aCal.get(Calendar.DAY_OF_MONTH),aCal.get(Calendar.MONTH) + 1,aCal.get(Calendar.YEAR),aCal.get(Calendar.HOUR_OF_DAY),aCal.get(Calendar.MINUTE))
            val flightTime = route.aTimeUTC - route.dTimeUTC
            val flightHours = flightTime / 3600
            val flightMinutes = (flightTime / 60) % 60
            fly_time.text = context.getString(R.string.fly_time,flightHours,flightMinutes)

            val placeholder = CircularProgressDrawable(context)
            placeholder.start()

            Glide.with(airline_logo)
                .load(ServerContract.createAirlineLogoImageUrl(route.airline))
                .downsample(DownsampleStrategy.FIT_CENTER)
                .placeholder(placeholder)
                .error(R.drawable.ic_error)
                .into(airline_logo)

            airline.text = route.airline
        }
    }

    inner class ViewHolder(val mRoot: View) : RecyclerView.ViewHolder(mRoot)

}