package mx.com.exploremars.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import mx.com.exploremars.R
import mx.com.exploremars.data.repository.mars.model.Camera

class CamerasAdapter(private val cameras: List<Camera>, private val selected: (Camera) -> Unit): Adapter<CamerasAdapter.CameraVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CameraVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.camera_card, parent, false)
        return CameraVH(view)
    }

    override fun onBindViewHolder(holder: CameraVH, position: Int) {
        holder.bind(cameras[position])
    }

    override fun getItemCount(): Int = cameras.size

    inner class CameraVH(itemView: View): ViewHolder(itemView) {

        fun bind(camera: Camera) {
            itemView.findViewById<TextView>(R.id.cameraName).text = camera.name
            itemView.setOnClickListener { selected(camera) }
        }
    }
}