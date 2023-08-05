package com.example.pruebarapidapi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.pruebarapidapi.databinding.FragmentAnimeInfoBinding
import com.example.pruebarapidapi.models.AnimeItem
import com.example.pruebarapidapi.paging.StringAdapter

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
    ): View {
        binding = FragmentAnimeInfoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        binding.apply {
            imvAnime.load(anime.image)
            tvAnimeName.text = anime.title
            tvAnimeStatus.text = anime.status
            val dataList: ArrayList<String> = anime.alternativeTitles
            val adapter = StringAdapter(requireContext(), dataList)
            rvAlternativeTitles.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvAlternativeTitles.adapter = adapter
        }
    }
}