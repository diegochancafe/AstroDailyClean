package pe.edu.utp.astrodailyclean.data.remote.service

import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitApi {
    @GET("apod")
    suspend fun getPhoto(
        @Query("api_key") apiKey: String,
        @Query("start_date") date: String,
        @Query("end_date") endDate: String
    ): Response<List<AstronomyPhotoResponse>>
}