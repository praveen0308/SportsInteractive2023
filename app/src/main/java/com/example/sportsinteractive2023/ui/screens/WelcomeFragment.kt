package com.example.sportsinteractive2023.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sportsinteractive2023.R
import com.example.sportsinteractive2023.databinding.FragmentMatchDetailBinding
import com.example.sportsinteractive2023.databinding.FragmentWelcomeBinding
import com.example.sportsinteractive2023.models.MatchModel
import com.example.sportsinteractive2023.ui.adapters.MatchAdapter

class WelcomeFragment : Fragment(), MatchAdapter.MatchListInterface {

    private lateinit var matchListAdapter: MatchAdapter
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateRvMatchList()

    }

    private fun initiateRvMatchList() {
        matchListAdapter = MatchAdapter(this)
        binding.rvMatchList.apply {
            setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            this.layoutManager = layoutManager
            adapter = matchListAdapter
        }

        val matchList = mutableListOf<MatchModel>()
        matchList.add(MatchModel("nzin01312019187360", "India VS New Zealand", ""))
        matchList.add(MatchModel("sapk01222019186652", "South Africa VS Pakistan", ""))

        matchListAdapter.setMatchList(matchList)
    }

    override fun onItemClick(item: MatchModel) {
        findNavController().navigate(
            WelcomeFragmentDirections.actionWelcomeFragmentToMatchDetailFragment(
                item.code
            )
        )
    }


}