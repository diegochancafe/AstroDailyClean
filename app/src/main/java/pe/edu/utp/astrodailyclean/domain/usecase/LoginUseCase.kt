package pe.edu.utp.astrodailyclean.domain.usecase

import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String) {
        repository.saveSession(username)
    }
}
