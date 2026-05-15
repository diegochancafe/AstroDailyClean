package pe.edu.utp.astrodailyclean.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun saveSession(username: String)
    suspend fun clearSession()
    fun getSession(): Flow<String?>
}
