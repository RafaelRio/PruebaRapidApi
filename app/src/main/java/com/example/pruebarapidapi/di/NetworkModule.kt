package com.example.pruebarapidapi.di

import com.example.pruebarapidapi.retrofit.AnimeAPI
import com.example.pruebarapidapi.retrofit.TranslationAPI
import com.example.pruebarapidapi.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("mainRetrofit")
    fun provideRetrofitMain(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /*@Provides
    @Singleton
    @Named("translateRetrofit")
    fun provideRetrofitTranslate(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.TRANSLATE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
*/
    @Provides
    @Singleton
    fun provideQuoteApi(@Named("mainRetrofit") retrofit: Retrofit): AnimeAPI =
        retrofit.create(AnimeAPI::class.java)

    /*@Provides
    @Singleton
    fun provideTranslateApi(@Named("translateRetrofit") retrofit: Retrofit): TranslationAPI =
        retrofit.create(TranslationAPI::class.java)
        */

}