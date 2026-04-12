package pe.edu.utp.astrodailyclean.data.remote.datasource

import pe.edu.utp.astrodailyclean.BuildConfig
import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse
import pe.edu.utp.astrodailyclean.data.remote.service.IRetrofitApi
import javax.inject.Inject

class AstronomyPhotoRemoteDataSourceImpl @Inject constructor(
    private val api: IRetrofitApi
) : AstronomyPhotoRemoteDataSource {

    override suspend fun fetchPhotos(startDate: String, endDate: String): List<AstronomyPhotoResponse> {
        return try {
            val response = api.getPhoto(BuildConfig.NASA_API_KEY, startDate, endDate)
            if (response.isSuccessful) {
                response.body() ?: emptyList()  // if response is successful then return body
            } else {
                emptyList() // if error is response then return empty list
            }
        } catch (e: Exception) {
            emptyList() // if exception is thrown then return empty list
        }
    }
}