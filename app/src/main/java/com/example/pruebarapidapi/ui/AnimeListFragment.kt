package com.example.pruebarapidapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.pruebarapidapi.databinding.FragmentAnimeListBinding
import com.example.pruebarapidapi.model.AnimeItem
import kotlinx.coroutines.launch
import kotlin.random.Random

class AnimeListFragment : Fragment() {

    private lateinit var binding: FragmentAnimeListBinding
    private lateinit var miViewModel: AnimeViewModel
    private var animeList: List<AnimeItem>? = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        miViewModel = ViewModelProvider(this)[AnimeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            animeList = miViewModel.getAnimes()
        }

        binding.apply {
            txt.text = animeList.toString()

            btn.setOnClickListener {
                lifecycleScope.launch {
                    animeList = miViewModel.getAnimes()
                    val random = Random.nextInt(0, animeList!!.size)
                    txt.text = animeList?.get(random)!!.title
                }
            }
        }
    }
}