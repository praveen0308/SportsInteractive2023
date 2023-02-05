package com.example.sportsinteractive2023.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsinteractive2023.databinding.FragmentMatchDetailBinding
import com.example.sportsinteractive2023.models.MatchModel
import com.example.sportsinteractive2023.ui.adapters.MatchAdapter
import com.example.sportsinteractive2023.viewmodels.MainPageState
import com.example.sportsinteractive2023.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MatchDetailFragment : Fragment() {

    private var _binding: FragmentMatchDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MainViewModel>()

    private val args by navArgs<MatchDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObservers()

        viewModel.fetchMatchDetails(args.matchCode)
        binding.btnInfo.setOnClickListener {
            findNavController().navigate(MatchDetailFragmentDirections.actionMatchDetailFragmentToTeamNPlayerInformationFragment())
        }
    }



    private fun showLoading(flag: Boolean) {
        binding.pgLoader.visibility = if (flag) View.VISIBLE else View.GONE
    }

    private fun subscribeObservers() {
        viewModel.mainPageState.observe(viewLifecycleOwner) { state ->
            showLoading(false)
            binding.tvResponse.isVisible = false
            when (state) {
                is MainPageState.Error -> {
                    Toast.makeText(requireContext(),state.msg,Toast.LENGTH_LONG).show()
                }
                MainPageState.Idle -> {}
                MainPageState.Loading -> showLoading(true)
                MainPageState.NoInternet -> {
                    Timber.e("No Internet !!!")
                    binding.apply {
                        tvResponse.isVisible = true

                        tvResponse.text = "No Internet!!!"
                    }
                }
                is MainPageState.Processing -> {}
                is MainPageState.ReceivedMatchDetails -> {
                    binding.apply {
                        btnInfo.isVisible =true

                        tvMatchOpponents.text = state.data.getOpponents()
                        tvResult.text = state.data.Matchdetail!!.Result
                        tvVenue.text = state.data.Matchdetail.Venue?.Name
                        tvDateTime.text =
                            "${state.data.Matchdetail?.Match?.Date} ${state.data.Matchdetail?.Match?.Time} ${state.data.Matchdetail?.Match?.Offset}"
                    }
                }
            }
        }
    }


}