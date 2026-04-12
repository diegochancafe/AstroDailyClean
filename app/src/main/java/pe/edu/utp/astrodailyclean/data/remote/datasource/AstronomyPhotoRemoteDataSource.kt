package pe.edu.utp.astrodailyclean.data.remote.datasource

import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse

interface AstronomyPhotoRemoteDataSource {
    suspend fun fetchPhotos(startDate: String, endDate: String): List<AstronomyPhotoResponse>
}