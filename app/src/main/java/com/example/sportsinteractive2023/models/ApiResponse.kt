package com.example.sportsinteractive2023.models

data class ApiResponse(
    val Innings: List<Inning>,
    val Matchdetail: Matchdetail?,
    val Notes: Notes,
    val Nuggets: List<String>?,
    val Teams: Map<String,Team>?
){
    fun getTeams():List<Team>{
        val teams = mutableListOf<Team>()
        Teams!!.forEach {
            it.value.TeamID = it.key
            teams.add(it.value)
        }

        return teams
    }
    fun getTeam1():Team = getTeams()[0]
    fun getTeam2():Team = getTeams()[1]

    fun getOpponents():String{
        return "${getTeam1().Name_Full} vs ${getTeam2().Name_Full}"
    }

}