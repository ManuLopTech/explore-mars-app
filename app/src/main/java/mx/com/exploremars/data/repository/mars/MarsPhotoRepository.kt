package mx.com.exploremars.data.repository.mars

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import mx.com.exploremars.data.repository.mars.model.PhotosResult
import mx.com.exploremars.data.repository.mars.model.RoversResult
import mx.com.exploremars.data.request.Request
import mx.com.exploremars.data.request.RequestInfo

class MarsPhotoRepository {

    suspend fun loadRovers(result: (RoversResult) -> Unit) = withContext(IO) {
        val requestInfo = RequestInfo(baseUrl = BASE_URL, url = "$BASE_URL$ROVERS")
        val response = Request().request(requestInfo)
        if (response.statusCode == 200) {
            val roversResult = Gson().fromJson(response.response, RoversResult::class.java)
            roversResult.success = true
            result(roversResult)
        } else {
            val roversResult = RoversResult(false, null, "Error al cargar rovers")
            result(roversResult)
        }
    }

    suspend fun loadPhotos(rover: String, params: Map<String, String>, result: (PhotosResult) -> Unit) = withContext(IO) {
        val url = "$BASE_URL${PHOTOS.replace("roverName", rover)}"
        val requestInfo = RequestInfo(baseUrl = BASE_URL, url = url, params = params)
        val response = Request().request(requestInfo)
        if (response.statusCode == 200) {
            val photosResult = Gson().fromJson(response.response, PhotosResult::class.java)
            photosResult.success = true
            result(photosResult)
        } else {
            val photosResult = PhotosResult(false, null, "Error al cargar photos")
            result(photosResult)
        }
    }

    companion object {

        const val BASE_URL = "https://mars-photos.herokuapp.com/"
        const val ROVERS = "api/v1/rovers"
        const val PHOTOS = "api/v1/rovers/roverName/photos"

        @Volatile
        private var instance: MarsPhotoRepository? = null

        fun getInstace(): MarsPhotoRepository = synchronized(this) {
            instance ?: MarsPhotoRepository().also { instance = it }
        }
    }
}