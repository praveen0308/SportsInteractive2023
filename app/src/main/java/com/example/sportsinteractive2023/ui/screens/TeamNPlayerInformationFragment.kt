package com.example.sportsinteractive2023.ui.screens

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sportsinteractive2023.R
import com.example.sportsinteractive2023.databinding.FragmentTeamNPlayerInformationBinding
import com.example.sportsinteractive2023.databinding.PlayerDetailBinding
import com.example.sportsinteractive2023.models.Player
import com.example.sportsinteractive2023.models.Team
import com.example.sportsinteractive2023.ui.adapters.PlayersListAdapter
import com.example.sportsinteractive2023.viewmodels.MainViewModel


class TeamNPlayerInformationFragment : Fragment(), PlayersListAdapter.PlayersListInterface {

    private lateinit var playersListAdapter: PlayersListAdapter
    private val viewModel by activityViewModels<MainViewModel>()
    private var _binding: FragmentTeamNPlayerInformationBinding? = null
    private val binding get() = _binding!!


    private val teams = mutableListOf<Team>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamNPlayerInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateRvPlayers()
        teams.clear()
        teams.addAll(viewModel.apiResponse.getTeams())
        updateList(0)
        binding.rgFilters.setOnCheckedChangeListener { group, i ->

            if(i==R.id.rb_all){
                updateList(0)
            }
            if(i==R.id.rb_team_1){
                updateList(1)
            }
            if(i==R.id.rb_team_2){
                updateList(2)
            }


        }
    }

    private fun updateList(index:Int){
        val players = mutableListOf<Player>()

        when(index){
            0 ->{
                teams.forEach {
                    players.addAll(it.getPlayers())
                }
            }
            1 -> {
                players.addAll(teams[0].getPlayers())
            }
            2 ->{
                players.addAll(teams[1].getPlayers())
            }
            else ->{}

        }

        playersListAdapter.setPlayerList(players)

    }

    private fun initiateRvPlayers() {
        playersListAdapter = PlayersListAdapter(this)
        binding.rvPlayers.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = playersListAdapter
        }

    }

    override fun onPlayerClick(item: Player) {
        showDialog(item)
    }

    private fun showDialog(player: Player) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)

        val playerViewBinding = PlayerDetailBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(playerViewBinding.root)
        playerViewBinding.tvName.text = player.Name_Full
        playerViewBinding.tvPosition.text = player.Position
        playerViewBinding.tvType.text = player.getPlayerType()
        player.Batting?.let {
            playerViewBinding.battingRow1.setValue(it.Style)
            playerViewBinding.battingRow2.setValue(it.Average)
            playerViewBinding.battingRow3.setValue(it.Strikerate)
            playerViewBinding.battingRow4.setValue(it.Runs)
        }

        player.Bowling?.let {
            playerViewBinding.bowlingRow1.setValue(it.Style)
            playerViewBinding.bowlingRow4.setValue(it.Average)
            playerViewBinding.bowlingRow3.setValue(it.Economyrate)
            playerViewBinding.bowlingRow4.setValue(it.Wickets)

        }

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.show()
        dialog.window?.attributes = lp


    }
}