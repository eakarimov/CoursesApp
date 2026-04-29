package com.example.coursesapp.presentation.home

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coursesapp.R
import com.example.coursesapp.databinding.FragmentHomeBinding
import com.example.coursesapp.presentation.home.adapter.HomeAdapter
import com.example.coursesapp.presentation.home.adapter.HomeItemDecoration
import com.example.coursesapp.presentation.home.model.HomeUiItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by hiltNavGraphViewModels(R.id.main_graph)
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()
        collectUiState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() = with(binding) {
        adapter = HomeAdapter {
            viewModel.sortByPublishDate()
        }

        rvCourses.adapter = adapter
        rvCourses.layoutManager = LinearLayoutManager(requireContext())
        rvCourses.addItemDecoration(HomeItemDecoration(topOffset = 16.dp))
    }

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { updateUi(it) }
            }
        }
    }

    private fun updateUi(uiState: HomeUiState) = with(binding) {
        when {
            uiState.isLoading -> {
                loadingInd.isVisible = true
                rvCourses.isVisible = false
            }

            uiState.courses.isNotEmpty() -> {
                adapter.items = buildList {
                    add(HomeUiItem.Search)
                    add(HomeUiItem.Sort)
                    addAll(uiState.courses)
                }

                loadingInd.isVisible = false
                rvCourses.isVisible = true
            }
        }
    }
}