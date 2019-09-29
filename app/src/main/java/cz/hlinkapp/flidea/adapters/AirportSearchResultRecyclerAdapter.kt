package cz.hlinkapp.flidea.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cz.hlinkapp.flidea.R
import cz.hlinkapp.flidea.model.Airport
import kotlinx.android.synthetic.main.item_airport.view.*

/**
 * A [RecyclerView] adapter for displaying airport search results.
 */
class AirportSearchResultRecyclerAdapter(val onSelectListener : ((airport: Airport) -> Unit)? ) : RecyclerView.Adapter<AirportSearchResultRecyclerAdapter.ViewHolder>() {

    /**
     * The airports that should be displayed. 
     * The view will by automatically notified of dataSet changes when this field is udpdated.
     */
    var airports = ArrayList<Airport>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_airport,parent,false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int = airports.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with (holder.mRoot) {
            val airport = airports[position]
            airportName.text = airport.name
            setOnClickListener { onSelectListener?.invoke(airport) }
        }
    }

    inner class ViewHolder(val mRoot: View) : RecyclerView.ViewHolder(mRoot)

}