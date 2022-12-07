package mx.com.exploremars.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import mx.com.exploremars.data.repository.mars.model.Rover
import mx.com.exploremars.databinding.ActivityMarsBinding
import mx.com.exploremars.ui.adapters.CamerasAdapter
import mx.com.exploremars.ui.adapters.PhotosAdapter
import mx.com.exploremars.ui.adapters.RoversAdapter
import mx.com.exploremars.viewmodels.MarsPhotoViewModel
import mx.com.exploremars.viewmodels.MarsPhotoViewModelFactory

class MarsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarsBinding
    private lateinit var marsPhotoViewModel: MarsPhotoViewModel
    private val params: MutableMap<String, String> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        marsPhotoViewModel = ViewModelProvider(this, MarsPhotoViewModelFactory.get())[MarsPhotoViewModel::class.java]
        setupObservers()
        marsPhotoViewModel.loadRovers()
    }

    private fun setupObservers() {
        marsPhotoViewModel.roversResult.observe(this) { rovers ->
            var isFirstLaunch = false
            if (binding.roversRecycler.adapter == null) isFirstLaunch = true
            binding.roversRecycler.adapter = RoversAdapter(rovers) { onRoverSelected(it) }
            if (isFirstLaunch) onRoverSelected(rovers[0])
        }
        marsPhotoViewModel.photosResult.observe(this) { photos ->
            binding.photosRecycler.adapter = PhotosAdapter(photos) {}
        }
    }

    private fun onRoverSelected(rover: Rover) {
        binding.roverTitle.text = rover.name
        binding.roverLaunch.text = rover.launchDate
        binding.roverPhotos.text = rover.totalPhotos.toString()
        binding.roverLast.text = rover.maxDate
        binding.camerasRecycler.adapter = CamerasAdapter(rover.cameras) {
            params["camera"] = it.name
            params["page"] = "1"
            marsPhotoViewModel.loadPhotos(rover.name, params)
        }
        params["earth_date"] = "2017-4-5"
        params["page"] = "1"
        params.remove("camera")
        marsPhotoViewModel.loadPhotos(rover.name, params)
    }

}