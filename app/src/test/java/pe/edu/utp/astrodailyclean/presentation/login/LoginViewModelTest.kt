package pe.edu.utp.astrodailyclean.presentation.login

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.usecase.LoginUseCase
import pe.edu.utp.astrodailyclean.domain.usecase.ValidateCredentialsUseCase
import pe.edu.utp.astrodailyclean.domain.usecase.ValidationResult

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val validateCredentialsUseCase: ValidateCredentialsUseCase = mockk()
    private val loginUseCase: LoginUseCase = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = LoginViewModel(validateCredentialsUseCase, loginUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when onUsernameChanged is called, then state should be updated`() {
        val username = "testUser"
        viewModel.onUsernameChanged(username)
        assertEquals(username, viewModel.state.value.username)
        assertNull(viewModel.state.value.error)
    }

    @Test
    fun `when onPasswordChanged is called, then state should be updated`() {
        val password = "password123"
        viewModel.onPasswordChanged(password)
        assertEquals(password, viewModel.state.value.password)
        assertNull(viewModel.state.value.error)
    }

    @Test
    fun `when onLoginClicked with invalid credentials, then error state should be updated`() {
        // Given
        val errorMessage = "Invalid"
        coEvery { validateCredentialsUseCase(any(), any()) } returns ValidationResult.Error(errorMessage)

        // When
        viewModel.onLoginClicked()

        // Then
        assertEquals(errorMessage, viewModel.state.value.error)
        assertFalse(viewModel.state.value.isSuccess)
    }

    @Test
    fun `when onLoginClicked with valid credentials, then success state should be updated`() = runTest {
        // Given
        coEvery { validateCredentialsUseCase(any(), any()) } returns ValidationResult.Success
        coEvery { loginUseCase(any()) } returns Unit

        // When
        viewModel.onLoginClicked()

        // Then
        assertTrue(viewModel.state.value.isSuccess)
        assertNull(viewModel.state.value.error)
    }
}
