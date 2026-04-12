package pe.edu.utp.astrodailyclean.domain.model

data class AstronomyPhoto(
    val copyright: String,
    val date: String,
    val explanation: String,
    val highDefinitionUrl: String,
    val mediaType: String,
    val serviceVersion: String,
    val title: String,
    val url: String
)