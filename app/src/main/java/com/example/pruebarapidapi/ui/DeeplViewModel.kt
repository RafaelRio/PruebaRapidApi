package com.example.pruebarapidapi.ui

import androidx.lifecycle.ViewModel
import com.example.pruebarapidapi.models.Translation
import com.example.pruebarapidapi.repository.AnimeRepository
import com.example.pruebarapidapi.repository.TranslationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeeplViewModel @Inject constructor(
    private val deeplRepository: TranslationRepository,
) : ViewModel() {

    fun getTranslatedText(text: String): String {
        return deeplRepository.getTranslation(text)[0].translatedText
    }
}