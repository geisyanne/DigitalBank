package co.geisyanne.digitalbank.di

import co.geisyanne.digitalbank.data.repository.auth.AuthFirebaseDataSource
import co.geisyanne.digitalbank.data.repository.auth.AuthFirebaseDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsAuthRepository(
        authFirebaseDataSourceImpl: AuthFirebaseDataSourceImpl
    ): AuthFirebaseDataSource  // retorna interface




}