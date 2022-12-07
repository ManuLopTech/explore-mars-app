package mx.com.exploremars.data.repository.mars.model

import com.google.gson.annotations.SerializedName

data class RoversResult(
    var success: Boolean,
    @SerializedName("rovers") val rovers: List<Rover>?,
    var error: String
)

data class Rover(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("landing_date") val landingDate: String,
    @SerializedName("launch_date") val launchDate: String,
    @SerializedName("status") val status: String,
    @SerializedName("max_sol") val maxSol: Int,
    @SerializedName("max_date") val maxDate: String,
    @SerializedName("total_photos") val totalPhotos: Int,
    @SerializedName("cameras") val cameras: List<Camera>
)

data class Camera(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("rover_id") val roverId: Int,
)
