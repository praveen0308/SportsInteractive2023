package com.example.sportsinteractive2023.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsinteractive2023.databinding.MatchViewBinding
import com.example.sportsinteractive2023.databinding.PlayerViewBinding
import com.example.sportsinteractive2023.models.MatchModel
import com.example.sportsinteractive2023.models.Player

class PlayersListAdapter(private val mListener: PlayersListInterface) : RecyclerView.Adapter<PlayersListAdapter.PlayerViewHolder>() {

    private val players = mutableListOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),mListener
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int {
        return players.size
    }

    fun setPlayerList(mList:List<Player>){
        this.players.clear();
        this.players.addAll(mList)
        notifyDataSetChanged()
    }

    inner class PlayerViewHolder(
        private val binding: PlayerViewBinding,
        private val mListener:PlayersListInterface
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                mListener.onPlayerClick(players[adapterPosition])
            }
        }

        fun bind(item: Player) {
            binding.apply {
                tvPlayerProfile.text = item.Position
                tvPlayerName.text = item.Name_Full
                tvPlayerType.text = item.getPlayerType()
            }
        }
    }

    interface PlayersListInterface{
        fun onPlayerClick(item:Player)
    }


}