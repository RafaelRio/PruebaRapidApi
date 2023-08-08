package com.example.pruebarapidapi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AnimeItem(
    val _id: String,
    val alternativeTitles: ArrayList<String>,
    val episodes: Int,
    val genres: ArrayList<String>,
    val hasEpisode: Boolean,
    val hasRanking: Boolean,
    val image: String,
    val link: String,
    val ranking: Int,
    val status: String,
    val synopsis: String,
    val thumb: String,
    val title: String,
    val type: String
): Serializable

data class Genre(val _id: String)

data class AnimeResponse(@SerializedName("data") val animeList: List<AnimeItem>)

data class Genres(val genreList: List<Genre>)
