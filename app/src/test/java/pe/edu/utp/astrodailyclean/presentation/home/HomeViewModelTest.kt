package pe.edu.utp.astrodailyclean.presentation.home

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.usecase.CheckSessionUseCase
import pe.edu.utp.astrodailyclean.domain.usecase.LogoutUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val checkSessionUseCase: CheckSessionUseCase = mockk()
    private val logoutUseCase: LogoutUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { checkSessionUseCase() } returns flowOf("admin")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when initialized, then it should load username`() = runTest {
        // Given
        val expectedUsername = "admin"
        coEvery { checkSessionUseCase() } returns flowOf(expectedUsername)

        // When
        viewModel = HomeViewModel(checkSessionUseCase, logoutUseCase)

        // Then
        assertEquals(expectedUsername, viewModel.username.value)
    }

    @Test
    fun `when onLogoutClicked is called, then it should call logoutUseCase and update state`() = runTest {
        // Given
        viewModel = HomeViewModel(checkSessionUseCase, logoutUseCase)
        coEvery { logoutUseCase() } returns Unit

        // When
        viewModel.onLogoutClicked()

        // Then
        coVerify(exactly = 1) { logoutUseCase() }
        assertTrue(viewModel.isLoggedOut.value)
    }
}
