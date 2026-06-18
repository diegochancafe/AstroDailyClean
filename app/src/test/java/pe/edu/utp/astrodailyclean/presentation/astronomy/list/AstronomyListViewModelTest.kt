package pe.edu.utp.astrodailyclean.presentation.astronomy.list

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.model.AstronomyPhoto
import pe.edu.utp.astrodailyclean.domain.usecase.GetAstronomyPhotosUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class AstronomyListViewModelTest {

    private lateinit var viewModel: AstronomyListViewModel
    private val getPhotosUseCase: GetAstronomyPhotosUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when loadPhotos is success, then update photos state`() = runTest {
        // Given
        val photos = listOf(
            AstronomyPhoto("c1", "2024-01-01", "exp", "hd", "type", "v1", "Title", "url")
        )
        coEvery { getPhotosUseCase(any(), any()) } returns photos

        // When
        viewModel = AstronomyListViewModel(getPhotosUseCase)

        // Then
        assertEquals(photos, viewModel.photos.value)
        assertFalse(viewModel.isLoading.value)
        assertNull(viewModel.error.value)
    }

    @Test
    fun `when loadPhotos fails, then update error state`() = runTest {
        // Given
        val errorMessage = "Network Error"
        coEvery { getPhotosUseCase(any(), any()) } throws Exception(errorMessage)

        // When
        viewModel = AstronomyListViewModel(getPhotosUseCase)

        // Then
        assertEquals(errorMessage, viewModel.error.value)
        assertTrue(viewModel.photos.value.isEmpty())
        assertFalse(viewModel.isLoading.value)
    }
}
