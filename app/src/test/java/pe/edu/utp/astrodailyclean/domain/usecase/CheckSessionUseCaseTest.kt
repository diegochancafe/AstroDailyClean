package pe.edu.utp.astrodailyclean.domain.usecase

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository

class CheckSessionUseCaseTest {

    private lateinit var checkSessionUseCase: CheckSessionUseCase
    private val repository: AuthRepository = mockk()

    @Before
    fun setUp() {
        checkSessionUseCase = CheckSessionUseCase(repository)
    }

    @Test
    fun `when invoke is called, then it should return session from repository`() = runTest {
        // Given
        val expectedUsername = "admin"
        coEvery { repository.getSession() } returns flowOf(expectedUsername)

        // When
        val resultFlow = checkSessionUseCase()

        // Then
        resultFlow.collect { username ->
            assertEquals(expectedUsername, username)
        }
    }
}
