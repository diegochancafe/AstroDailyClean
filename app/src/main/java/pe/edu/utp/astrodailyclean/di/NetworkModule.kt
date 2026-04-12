package pe.edu.utp.astrodailyclean.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pe.edu.utp.astrodailyclean.core.network.NetworkConstants
import pe.edu.utp.astrodailyclean.data.remote.datasource.AstronomyPhotoRemoteDataSource
import pe.edu.utp.astrodailyclean.data.remote.datasource.AstronomyPhotoRemoteDataSourceImpl
import pe.edu.utp.astrodailyclean.data.remote.service.IRetrofitApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val timeout = (60 * 1000) // 60 Seconds
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): IRetrofitApi {
        return retrofit.create(IRetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(api: IRetrofitApi): AstronomyPhotoRemoteDataSource =
        AstronomyPhotoRemoteDataSourceImpl(api)
}