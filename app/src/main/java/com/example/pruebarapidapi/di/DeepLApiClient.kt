package com.example.pruebarapidapi.di

import com.example.pruebarapidapi.retrofit.TranslationAPI
import com.example.pruebarapidapi.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DeepLApiClient {
    private const val BASE_URL = "https://api-free.deepl.com/v2/"


    private val deepLService: TranslationAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TranslationAPI::class.java)
    }

    @Provides
    @Singleton
    fun getTranslationService(): TranslationAPI = deepLService
}