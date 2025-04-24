package co.geisyanne.digitalbank.presenter.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.geisyanne.digitalbank.domain.auth.LoginUseCase
import co.geisyanne.digitalbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(email: String, password: String) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            loginUseCase.invoke(email, password)

            emit(StateView.Success(null))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }


    }


}