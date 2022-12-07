package mx.com.exploremars.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import mx.com.exploremars.data.repository.mars.MarsPhotoRepository
import org.junit.Test

class MarsPhotoRepositoryTest {

    @ExperimentalCoroutinesApi
    @Test
    fun loadRoversTest() = runTest {
        launch {
            MarsPhotoRepository().loadRovers {
                assert(it.success)
                assert(it.rovers != null)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun loadPhotosTest() = runTest {
        val rover = "curiosity"
        val params: MutableMap<String, String> = mutableMapOf()
        params["sol"] = "1000"
        params["camera"] = "fhaz"
        params["page"] = "1"
        launch {
            MarsPhotoRepository().loadPhotos(rover, params) {
                assert(it.success)
                assert(it.photos != null)
            }
        }
    }

}