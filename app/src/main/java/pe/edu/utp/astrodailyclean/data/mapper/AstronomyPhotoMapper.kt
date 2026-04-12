package pe.edu.utp.astrodailyclean.data.mapper

import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto

fun AstronomyPhotoResponse.toDomain() = AstronomyPhoto(
    copyright = copyright ?: "",
    date = date ?: "",
    explanation = explanation ?: "",
    highDefinitionUrl = highDefinitionUrl ?: "",
    mediaType = mediaType ?: "",
    serviceVersion = serviceVersion ?: "",
    title = title ?: "",
    url = url ?: ""
)