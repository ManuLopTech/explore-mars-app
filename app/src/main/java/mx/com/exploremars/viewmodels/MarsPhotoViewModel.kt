package mx.com.exploremars.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.com.exploremars.App
import mx.com.exploremars.data.repository.mars.MarsPhotoRepository
import mx.com.exploremars.data.repository.mars.model.Photo
import mx.com.exploremars.data.repository.mars.model.Rover

class MarsPhotoViewModel(private val repository: MarsPhotoRepository): ViewModel() {

    val roversResult = MutableLiveData<List<Rover>>()
    val roversError = MutableLiveData<String>()

    val photosResult = MutableLiveData<List<Photo>>()
    val photosError = MutableLiveData<String>()

    fun loadRovers() = viewModelScope.launch {
        repository.loadRovers { result ->
            if (result.success && result.rovers != null) {
                App.executors.main().execute { roversResult.value = result.rovers!! }
            } else {
                App.executors.main().execute { roversError.value = result.error }
            }
        }
    }

    fun loadPhotos(rover: String, params: Map<String, String>) = viewModelScope.launch {
        repository.loadPhotos(rover, params) { result ->
            if (result.success && result.photos != null) {
                App.executors.main().execute { photosResult.value = result.photos!! }
            } else {
                App.executors.main().execute { photosError.value = result.error ?: "Error al cargar" }
            }
        }
    }
}