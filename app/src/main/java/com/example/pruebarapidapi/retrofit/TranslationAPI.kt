package com.example.pruebarapidapi.retrofit

import com.example.pruebarapidapi.models.TranslationResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TranslationAPI {

    @FormUrlEncoded
    @POST("translate")
    fun translateText(
        @Field("auth_key") apiKey: String,
        @Field("text") text: String,
        @Field("target_lang") targetLang: String
    ): Call<TranslationResponse>
}