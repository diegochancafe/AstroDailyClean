package pe.edu.utp.astrodailyclean.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.data.remote.datasource.AstronomyPhotoRemoteDataSource
import pe.edu.utp.astrodailyclean.data.remote.dto.AstronomyPhotoResponse

class AstronomyPhotoRepositoryImplTest {

    private lateinit var repository: AstronomyPhotoRepositoryImpl
    private val remoteDataSource: AstronomyPhotoRemoteDataSource = mockk()

    @Before
    fun setUp() {
        repository = AstronomyPhotoRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `when fetchPhotos is success, then return list of domain photos`() = runTest {
        // Given
        val remotePhotos = listOf(
            AstronomyPhotoResponse(
                copyright = "copy",
                date = "2024-01-01",
                explanation = "exp",
                highDefinitionUrl = "hd",
                mediaType = "image",
                serviceVersion = "v1",
                title = "Title",
                url = "url"
            )
        )
        coEvery { remoteDataSource.fetchPhotos(any(), any()) } returns remotePhotos

        // When
        val result = repository.fetchPhotos("start", "end")

        // Then
        assertEquals(1, result.size)
        assertEquals("Title", result[0].title)
    }

    @Test
    fun `when fetchPhotos fails, then return empty list`() = runTest {
        // Given
        coEvery { remoteDataSource.fetchPhotos(any(), any()) } throws Exception("Network Error")

        // When
        val result = repository.fetchPhotos("start", "end")

        // Then
        assertTrue(result.isEmpty())
    }
}
