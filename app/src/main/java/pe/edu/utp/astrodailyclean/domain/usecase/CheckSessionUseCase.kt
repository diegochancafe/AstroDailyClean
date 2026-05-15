package pe.edu.utp.astrodailyclean.domain.usecase

import kotlinx.coroutines.flow.Flow
import pe.edu.utp.astrodailyclean.domain.repository.AuthRepository
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<String?> {
        return repository.getSession()
    }
}
