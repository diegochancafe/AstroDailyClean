package pe.edu.utp.astrodailyclean.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.edu.utp.astrodailyclean.domain.usecase.LoginUseCase
import pe.edu.utp.astrodailyclean.domain.usecase.ValidateCredentialsUseCase
import pe.edu.utp.astrodailyclean.domain.usecase.ValidationResult
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateCredentialsUseCase: ValidateCredentialsUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onUsernameChanged(username: String) {
        _state.update { it.copy(username = username, error = null) }
    }

    fun onPasswordChanged(password: String) {
        _state.update { it.copy(password = password, error = null) }
    }

    fun onLoginClicked() {
        val result = validateCredentialsUseCase(_state.value.username, _state.value.password)
        
        when (result) {
            is ValidationResult.Success -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true) }
                    loginUseCase(_state.value.username)
                    _state.update { it.copy(isLoading = false, isSuccess = true) }
                }
            }
            is ValidationResult.Error -> {
                _state.update { it.copy(error = result.message) }
            }
        }
    }
}
