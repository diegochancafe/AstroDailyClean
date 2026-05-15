package pe.edu.utp.astrodailyclean.data.repository

import kotlinx.coroutines.flow.Flow
import pe.edu.utp.astrodailyclean.data.datastore.SessionDataStore
import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sessionDataStore: SessionDataStore
) : AuthRepository {
    override suspend fun saveSession(username: String) {
        sessionDataStore.saveSession(username)
    }

    override suspend fun clearSession() {
        sessionDataStore.clearSession()
    }

    override fun getSession(): Flow<String?> {
        return sessionDataStore.getSession()
    }
}
