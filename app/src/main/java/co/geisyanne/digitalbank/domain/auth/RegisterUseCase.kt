package co.geisyanne.digitalbank.domain.auth

import co.geisyanne.digitalbank.data.model.User
import co.geisyanne.digitalbank.data.repository.auth.AuthFirebaseDataSourceImpl
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
) {

    suspend operator fun invoke(user: User) : User {
        return authFirebaseDataSourceImpl.register(user)
    }


}