package pe.edu.utp.astrodailyclean.data.repository

import pe.edu.utp.astrodailyclean.data.mapper.toDomain
import pe.edu.utp.astrodailyclean.data.remote.datasource.AstronomyPhotoRemoteDataSource
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.domain.repository.AstronomyPhotoRepository
import javax.inject.Inject

class AstronomyPhotoRepositoryImpl @Inject constructor(
    private val remoteDataSource: AstronomyPhotoRemoteDataSource
) : AstronomyPhotoRepository {

    override suspend fun fetchPhotos(startDate: String, endDate: String): List<AstronomyPhoto> {
        return try {
            remoteDataSource.fetchPhotos(startDate, endDate)
                .map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}