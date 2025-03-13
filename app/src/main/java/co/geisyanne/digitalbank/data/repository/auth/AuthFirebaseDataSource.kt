package co.geisyanne.digitalbank.data.repository.auth

import co.geisyanne.digitalbank.data.model.User

interface AuthFirebaseDataSource {

    suspend fun login(email: String, password: String)

    suspend fun register(user: User): User

    suspend fun recover(email: String)

}