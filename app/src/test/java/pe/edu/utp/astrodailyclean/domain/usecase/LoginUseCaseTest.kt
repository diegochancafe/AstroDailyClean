package pe.edu.utp.astrodailyclean.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository

class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase
    private val repository: AuthRepository = mockk()

    @Before
    fun setUp() {
        loginUseCase = LoginUseCase(repository)
    }

    @Test
    fun `when invoke is called, then it should save session in repository`() = runTest {
        // Given
        val username = "admin"
        coEvery { repository.saveSession(username) } returns Unit

        // When
        loginUseCase(username)

        // Then
        coVerify(exactly = 1) { repository.saveSession(username) }
    }
}
