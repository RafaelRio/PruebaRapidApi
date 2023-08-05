package com.example.pruebarapidapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.pruebarapidapi.R
import com.example.pruebarapidapi.databinding.FragmentAnimeInfoBinding
import com.example.pruebarapidapi.models.AnimeItem

class AnimeInfoFragment : Fragment() {

    private val args: AnimeInfoFragmentArgs by navArgs()
    private lateinit var anime: AnimeItem
    private lateinit var binding: FragmentAnimeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        anime = args.anime
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtSynopsis.text = anime.synopsis
    }
}