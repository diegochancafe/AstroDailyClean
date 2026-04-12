package pe.edu.utp.astrodailyclean.domain.usecase

import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.domain.repository.AstronomyPhotoRepository
import javax.inject.Inject

class GetAstronomyPhotosUseCase @Inject constructor(
    private val repository: AstronomyPhotoRepository
) {
    suspend operator fun invoke(startDate: String, endDate: String): List<AstronomyPhoto> {
        return repository.fetchPhotos(startDate, endDate)
    }
}