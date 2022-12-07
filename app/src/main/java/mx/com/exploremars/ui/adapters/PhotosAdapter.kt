package mx.com.exploremars.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import mx.com.exploremars.R
import mx.com.exploremars.data.repository.mars.model.Photo

class PhotosAdapter(private val photos: List<Photo>, private val selected: (Photo) -> Unit): Adapter<PhotosAdapter.PhotoVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_photo, parent, false)
        return PhotoVH(view)
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount(): Int = photos.size

    inner class PhotoVH(itemView: View): ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.photoImage)
        private val date = itemView.findViewById<TextView>(R.id.photoDate)

        fun bind(photo: Photo) {
            Glide.with(itemView.context).load(photo.imgSrc).into(image)
            date.text = photo.earthDate
            itemView.setOnClickListener { selected(photo) }
        }

    }

}