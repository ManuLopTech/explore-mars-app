package mx.com.exploremars.data.repository.mars.model

import com.google.gson.annotations.SerializedName

data class PhotosResult(
    var success: Boolean,
    @SerializedName("photos") val photos: List<Photo>?,
    var error: String?
)

data class Photo(
    @SerializedName("id") val id: Int,
    @SerializedName("sol") val sol: Int,
    @SerializedName("camera") val camera: Camera,
    @SerializedName("img_src") val imgSrc: String,
    @SerializedName("earth_date") val earthDate: String,
    @SerializedName("rover") val rover: Rover
)