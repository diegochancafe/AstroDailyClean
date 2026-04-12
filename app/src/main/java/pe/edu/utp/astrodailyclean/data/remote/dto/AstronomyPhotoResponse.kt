package pe.edu.utp.astrodailyclean.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AstronomyPhotoResponse(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    @SerializedName("hdurl")
    val highDefinitionUrl: String?, // URL for the high-definition image
    @SerializedName("media_type")
    val mediaType: String?,
    @SerializedName("service_version")
    val serviceVersion: String?,
    val title: String?,
    val url: String? // URL for the standard-quality image
)