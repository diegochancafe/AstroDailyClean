package pe.edu.utp.astrodailyclean.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.domain.repository.AstronomyPhotoRepository

class GetAstronomyPhotosUseCaseTest {

    private lateinit var getAstronomyPhotosUseCase: GetAstronomyPhotosUseCase
    private val repository: AstronomyPhotoRepository = mockk()

    @Before
    fun setUp() {
        getAstronomyPhotosUseCase = GetAstronomyPhotosUseCase(repository)
    }

    @Test
    fun `when invoke is called, then it should return list of photos from repository`() = runTest {
        // Given
        val startDate = "2023-10-01"
        val endDate = "2023-10-10"
        val expectedPhotos = listOf(
            AstronomyPhoto(
                copyright = "Copyright 1",
                date = "2023-10-01",
                explanation = "Explanation 1",
                highDefinitionUrl = "https://example.com/hd1.jpg",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Photo 1",
                url = "https://example.com/1.jpg"
            ),
            AstronomyPhoto(
                copyright = "Copyright 2",
                date = "2023-10-02",
                explanation = "Explanation 2",
                highDefinitionUrl = "https://example.com/hd2.jpg",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Photo 2",
                url = "https://example.com/2.jpg"
            )
        )
        coEvery { repository.fetchPhotos(startDate, endDate) } returns expectedPhotos

        // When
        val result = getAstronomyPhotosUseCase(startDate, endDate)

        // Then
        assertEquals(expectedPhotos, result)
        coVerify(exactly = 1) { repository.fetchPhotos(startDate, endDate) }
    }

    @Test
    fun `when repository returns empty list, then invoke should return empty list`() = runTest {
        // Given
        val startDate = "2023-10-01"
        val endDate = "2023-10-10"
        coEvery { repository.fetchPhotos(startDate, endDate) } returns emptyList()

        // When
        val result = getAstronomyPhotosUseCase(startDate, endDate)

        // Then
        assertEquals(emptyList<AstronomyPhoto>(), result)
        coVerify(exactly = 1) { repository.fetchPhotos(startDate, endDate) }
    }
}
