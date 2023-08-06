package com.example.pruebarapidapi.repository

import com.example.pruebarapidapi.models.Translation
import com.example.pruebarapidapi.models.TranslationResponse
import com.example.pruebarapidapi.retrofit.TranslationAPI
import com.example.pruebarapidapi.util.Constants
import javax.inject.Inject

class TranslationRepository @Inject constructor(
    private val translationApi: TranslationAPI,
) {
    fun getTranslation(text: String): List<Translation> {
        //return translationApi.translateText(Constants.deeplApiKey, text, "ES").translations
        return emptyList()
    }
}