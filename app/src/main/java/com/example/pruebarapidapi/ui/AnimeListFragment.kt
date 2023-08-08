package com.example.pruebarapidapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
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
    private lateinit var animePagingAdapter: AnimePagingAdapter


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
        initUi()
    }

    private fun initUi() {
        animePagingAdapter = AnimePagingAdapter(
            itemClickListener = { anime ->
                val action = AnimeListFragmentDirections.actionAnimeListFragmentToAnimeInfoFragment(anime)
                NavHostFragment.findNavController(this@AnimeListFragment).navigate(action)
            }
        )

        lifecycleScope.launch {
            loadAnimes()
        }
        binding.btnBuscar.setOnClickListener {
            miViewModel.filtersState.value.query = binding.tieName.text.toString()
            loadAnimes()
            //lifecycleScope.launch {
              //  miViewModel.getGenres()
            //}
        }

        binding.swipeToRefresh.setOnRefreshListener {
            loadAnimes()
            binding.swipeToRefresh.isRefreshing = false
        }

        binding.apply {
            recyclerView.adapter = animePagingAdapter
        }
    }

    fun loadAnimes() {
        lifecycleScope.launch {
            miViewModel.getAllAnimes(miViewModel.filtersState.value.query).flowWithLifecycle(lifecycle)
                .collect { animes ->
                    animePagingAdapter.submitData(animes)
                    binding.swipeToRefresh.isRefreshing = false
                }
        }
    }
}