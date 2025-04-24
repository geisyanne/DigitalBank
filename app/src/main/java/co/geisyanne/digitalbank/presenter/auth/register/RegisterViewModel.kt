package co.geisyanne.digitalbank.presenter.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import co.geisyanne.digitalbank.data.model.User
import co.geisyanne.digitalbank.domain.auth.RegisterUseCase
import co.geisyanne.digitalbank.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun register(user: User) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            registerUseCase.invoke(user)

            emit(StateView.Success(user))

        } catch (ex: Exception) {
            emit(StateView.Error(ex.message))
        }


    }


}