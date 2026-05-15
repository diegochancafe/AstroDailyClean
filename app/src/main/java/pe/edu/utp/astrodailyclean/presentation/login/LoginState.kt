package pe.edu.utp.astrodailyclean.presentation.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false
)
