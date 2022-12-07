package mx.com.exploremars.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import mx.com.exploremars.data.repository.mars.MarsPhotoRepository

class MarsPhotoViewModelFactory(private val repository: MarsPhotoRepository): ViewModelProvider.AndroidViewModelFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T = MarsPhotoViewModel(repository) as T

    companion object {
        fun get(): MarsPhotoViewModelFactory {
            val repo = MarsPhotoRepository.getInstace()
            return MarsPhotoViewModelFactory(repo)
        }
    }

}