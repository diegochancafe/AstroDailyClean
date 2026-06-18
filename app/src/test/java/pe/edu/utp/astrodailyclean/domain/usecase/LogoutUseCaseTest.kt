package pe.edu.utp.astrodailyclean.domain.usecase

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository

class LogoutUseCaseTest {

    private lateinit var logoutUseCase: LogoutUseCase
    private val repository: AuthRepository = mockk()

    @Before
    fun setUp() {
        logoutUseCase = LogoutUseCase(repository)
    }

    @Test
    fun `when invoke is called, then it should clear session in repository`() = runTest {
        // Given
        coEvery { repository.clearSession() } returns Unit

        // When
        logoutUseCase()

        // Then
        coVerify(exactly = 1) { repository.clearSession() }
    }
}
