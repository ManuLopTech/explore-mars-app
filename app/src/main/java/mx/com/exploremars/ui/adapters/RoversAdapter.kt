package mx.com.exploremars.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mx.com.exploremars.R
import mx.com.exploremars.data.repository.mars.model.Rover

class RoversAdapter(private val rovers: List<Rover>, private val selected: (Rover) -> Unit): Adapter<RoversAdapter.RoversVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoversVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rover_chip, parent, false)
        return RoversVH(view)
    }

    override fun getItemCount(): Int = rovers.size

    override fun onBindViewHolder(holder: RoversVH, position: Int) {
        holder.bind(rovers[position])
    }

    inner class RoversVH(itemView: View): ViewHolder(itemView) {

        fun bind(rover: Rover) {
            itemView.findViewById<TextView>(R.id.roverName).text = rover.name
            itemView.setOnClickListener { selected(rover) }
        }

    }
}