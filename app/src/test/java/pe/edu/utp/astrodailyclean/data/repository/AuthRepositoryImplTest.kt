package pe.edu.utp.astrodailyclean.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import pe.edu.utp.astrodailyclean.data.datastore.SessionDataStore

class AuthRepositoryImplTest {

    private lateinit var authRepository: AuthRepositoryImpl
    private val sessionDataStore: SessionDataStore = mockk()

    @Before
    fun setUp() {
        authRepository = AuthRepositoryImpl(sessionDataStore)
    }

    @Test
    fun `when saveSession is called, then it should call sessionDataStore`() = runTest {
        // Given
        val username = "admin"
        coEvery { sessionDataStore.saveSession(username) } returns Unit

        // When
        authRepository.saveSession(username)

        // Then
        coVerify(exactly = 1) { sessionDataStore.saveSession(username) }
    }

    @Test
    fun `when clearSession is called, then it should call sessionDataStore`() = runTest {
        // Given
        coEvery { sessionDataStore.clearSession() } returns Unit

        // When
        authRepository.clearSession()

        // Then
        coVerify(exactly = 1) { sessionDataStore.clearSession() }
    }

    @Test
    fun `when getSession is called, then it should return flow from sessionDataStore`() = runTest {
        // Given
        val expectedUsername = "admin"
        coEvery { sessionDataStore.getSession() } returns flowOf(expectedUsername)

        // When
        val resultFlow = authRepository.getSession()

        // Then
        resultFlow.collect { username ->
            assertEquals(expectedUsername, username)
        }
    }
}
