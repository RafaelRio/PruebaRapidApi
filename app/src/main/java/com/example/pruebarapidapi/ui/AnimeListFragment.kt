package com.example.pruebarapidapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.pruebarapidapi.databinding.FragmentAnimeListBinding
import com.example.pruebarapidapi.paging.AnimePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AnimeListFragment : Fragment() {

    private lateinit var binding: FragmentAnimeListBinding
    private lateinit var miViewModel: AnimeViewModel
    private val animeAdapter = AnimePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        miViewModel = ViewModelProvider(this)[AnimeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAnimeListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToEvents()

        initUi()
    }

    private fun initUi() {
        binding.apply {
            recyclerView.adapter = animeAdapter
        }
    }

    private fun subscribeToEvents() {
        lifecycleScope.launch {
            miViewModel.animeList.flowWithLifecycle(lifecycle)
                .collectLatest { animes ->
                    animeAdapter.submitData(animes)
                }
        }
    }
}