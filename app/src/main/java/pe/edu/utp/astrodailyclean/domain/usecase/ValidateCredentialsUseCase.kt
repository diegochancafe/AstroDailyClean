package pe.edu.utp.astrodailyclean.domain.usecase

import javax.inject.Inject

class ValidateCredentialsUseCase @Inject constructor() {
    operator fun invoke(username: String, password: String): ValidationResult {
        if (username.isBlank()) {
            return ValidationResult.Error("El usuario no puede estar vacío")
        }
        if (password.isBlank()) {
            return ValidationResult.Error("La contraseña no puede estar vacía")
        }
        if (username != "admin" || password != "123456") {
            return ValidationResult.Error("Credenciales incorrectas")
        }
        return ValidationResult.Success
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}
