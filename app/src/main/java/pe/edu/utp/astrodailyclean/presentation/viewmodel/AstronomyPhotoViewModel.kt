package pe.edu.utp.astrodailyclean.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.domain.usecase.GetAstronomyPhotosUseCase
import javax.inject.Inject

@HiltViewModel
class AstronomyPhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetAstronomyPhotosUseCase
) : ViewModel() {

    private val _photos = MutableStateFlow<List<AstronomyPhoto>>(emptyList())
    val photos: StateFlow<List<AstronomyPhoto>> = _photos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadPhotos("2026-03-30", "2026-04-02")
    }

    fun loadPhotos(startDate: String, endDate: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = getPhotosUseCase(startDate, endDate)
                _photos.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
                _photos.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}