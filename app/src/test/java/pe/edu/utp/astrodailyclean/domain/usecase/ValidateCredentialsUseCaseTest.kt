package pe.edu.utp.astrodailyclean.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateCredentialsUseCaseTest {

    private val validateCredentialsUseCase = ValidateCredentialsUseCase()

    @Test
    fun `when username is blank, then return Error`() {
        val result = validateCredentialsUseCase("", "123456")
        assertTrue(result is ValidationResult.Error)
        assertEquals("El usuario no puede estar vacío", (result as ValidationResult.Error).message)
    }

    @Test
    fun `when password is blank, then return Error`() {
        val result = validateCredentialsUseCase("admin", "")
        assertTrue(result is ValidationResult.Error)
        assertEquals("La contraseña no puede estar vacía", (result as ValidationResult.Error).message)
    }

    @Test
    fun `when credentials are incorrect, then return Error`() {
        val result = validateCredentialsUseCase("wrong", "wrong")
        assertTrue(result is ValidationResult.Error)
        assertEquals("Credenciales incorrectas", (result as ValidationResult.Error).message)
    }

    @Test
    fun `when credentials are valid, then return Success`() {
        val result = validateCredentialsUseCase("admin", "123456")
        assertEquals(ValidationResult.Success, result)
    }
}
