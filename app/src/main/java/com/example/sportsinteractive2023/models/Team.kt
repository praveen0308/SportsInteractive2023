package com.example.sportsinteractive2023.models

data class Team(
    @Transient
    var TeamID:String?,
    val Name_Full: String?,
    val Name_Short: String?,
    val Players: Map<String,Player>?
){
    fun getPlayers():List<Player>{
        val players = mutableListOf<Player>()
        Players!!.forEach {
            players.add(it.value)
        }

        return players
    }
}