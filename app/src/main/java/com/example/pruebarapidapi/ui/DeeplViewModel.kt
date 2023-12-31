package com.example.pruebarapidapi.ui

import androidx.lifecycle.ViewModel
import com.example.pruebarapidapi.di.DeepLApiClient
import com.example.pruebarapidapi.models.TranslationResponse
import com.example.pruebarapidapi.retrofit.TranslationAPI
import com.example.pruebarapidapi.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.awaitResponse
import javax.inject.Inject

@HiltViewModel
class DeeplViewModel @Inject constructor() : ViewModel() {

    suspend fun translateText(text: String, targetLang: String): String {
        val deepLService: TranslationAPI = DeepLApiClient.getTranslationService()
        val call: Call<TranslationResponse> = deepLService.translateText(Constants.deeplApiKey, text, targetLang)

        return try {
            val response = call.awaitResponse()
            if (response.isSuccessful) {
                val translationResponse: TranslationResponse? = response.body()
                return translationResponse?.translations?.get(0)?.translatedText ?: "AAA"
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }
}