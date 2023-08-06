package com.example.pruebarapidapi.models

import com.google.gson.annotations.SerializedName

data class TranslationResponse(
    @SerializedName("translations") val translations: List<Translation>
)

data class Translation(
    @SerializedName("text") val translatedText: String
)
