package pe.edu.utp.astrodailyclean.domain.repository

import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto

interface AstronomyPhotoRepository {
    suspend fun fetchPhotos(startDate: String, endDate: String): List<AstronomyPhoto>
}
