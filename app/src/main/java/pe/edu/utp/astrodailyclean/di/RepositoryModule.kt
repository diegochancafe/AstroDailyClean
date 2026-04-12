package pe.edu.utp.astrodailyclean.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pe.edu.utp.astrodailyclean.data.repository.AstronomyPhotoRepositoryImpl
import pe.edu.utp.astrodailyclean.domain.repository.AstronomyPhotoRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAstronomyPhotoRepository(
        impl: AstronomyPhotoRepositoryImpl
    ): AstronomyPhotoRepository
}