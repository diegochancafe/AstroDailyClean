package pe.edu.utp.astrodailyclean.domain.usecase

import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() {
        repository.clearSession()
    }
}
