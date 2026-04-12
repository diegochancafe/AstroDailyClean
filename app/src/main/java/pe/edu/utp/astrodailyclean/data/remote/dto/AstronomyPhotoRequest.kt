package pe.edu.utp.astrodailyclean.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AstronomyPhotoRequest(
    @SerializedName("api_key")
    val apiKey: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String
)