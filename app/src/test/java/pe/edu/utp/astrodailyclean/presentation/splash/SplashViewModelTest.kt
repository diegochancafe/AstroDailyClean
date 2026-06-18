package pe.edu.utp.astrodailyclean.presentation.splash

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.usecase.CheckSessionUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class SplashViewModelTest {

    private lateinit var viewModel: SplashViewModel
    private val checkSessionUseCase: CheckSessionUseCase = mockk()
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
    fun `when session exists, then isLoggedIn should be true`() = runTest {
        // Given
        coEvery { checkSessionUseCase() } returns flowOf("admin")

        // When
        viewModel = SplashViewModel(checkSessionUseCase)

        // Then
        assertTrue(viewModel.isLoggedIn.value == true)
    }

    @Test
    fun `when session does not exist, then isLoggedIn should be false`() = runTest {
        // Given
        coEvery { checkSessionUseCase() } returns flowOf(null)

        // When
        viewModel = SplashViewModel(checkSessionUseCase)

        // Then
        assertFalse(viewModel.isLoggedIn.value == true)
    }
}
