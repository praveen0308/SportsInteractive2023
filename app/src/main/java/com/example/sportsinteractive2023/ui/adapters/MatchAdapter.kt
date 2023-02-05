package com.example.sportsinteractive2023.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sportsinteractive2023.databinding.MatchViewBinding
import com.example.sportsinteractive2023.models.MatchModel

class MatchAdapter(private val mListener: MatchListInterface) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private val matchList = mutableListOf<MatchModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),mListener
        )
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    fun setMatchList(mList:List<MatchModel>){
        this.matchList.clear();
        this.matchList.addAll(mList)
        notifyDataSetChanged()
    }

    inner class MatchViewHolder(
        private val binding: MatchViewBinding,
        private val mListener:MatchListInterface
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                mListener.onItemClick(matchList[adapterPosition])
            }
        }

        fun bind(item: MatchModel) {
            binding.apply {
                tvTitle.text = item.matchName
            }
        }
    }

    interface MatchListInterface{
        fun onItemClick(item:MatchModel)
    }


}