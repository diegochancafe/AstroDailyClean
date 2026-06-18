package pe.edu.utp.astrodailyclean.data.mapper

import org.junit.Assert.assertEquals
import org.junit.Test
import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse

class AstronomyPhotoMapperTest {

    @Test
    fun `when map to domain, then all fields should be correctly mapped`() {
        // Given
        val response = AstronomyPhotoResponse(
            copyright = "NASA",
            date = "2024-01-01",
            explanation = "Beautiful star",
            highDefinitionUrl = "https://example.com/hd.jpg",
            mediaType = "image",
            serviceVersion = "v1",
            title = "Star",
            url = "https://example.com/sd.jpg"
        )

        // When
        val domain = response.toDomain()

        // Then
        assertEquals(response.copyright, domain.copyright)
        assertEquals(response.date, domain.date)
        assertEquals(response.explanation, domain.explanation)
        assertEquals(response.highDefinitionUrl, domain.highDefinitionUrl)
        assertEquals(response.mediaType, domain.mediaType)
        assertEquals(response.serviceVersion, domain.serviceVersion)
        assertEquals(response.title, domain.title)
        assertEquals(response.url, domain.url)
    }

    @Test
    fun `when map to domain with nulls, then fields should be empty strings`() {
        // Given
        val response = AstronomyPhotoResponse(
            copyright = null,
            date = null,
            explanation = null,
            highDefinitionUrl = null,
            mediaType = null,
            serviceVersion = null,
            title = null,
            url = null
        )

        // When
        val domain = response.toDomain()

        // Then
        assertEquals("", domain.copyright)
        assertEquals("", domain.date)
        assertEquals("", domain.explanation)
        assertEquals("", domain.highDefinitionUrl)
        assertEquals("", domain.mediaType)
        assertEquals("", domain.serviceVersion)
        assertEquals("", domain.title)
        assertEquals("", domain.url)
    }
}
