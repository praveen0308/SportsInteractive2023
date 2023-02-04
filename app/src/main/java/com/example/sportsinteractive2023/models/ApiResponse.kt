package com.example.sportsinteractive2023.models

data class ApiResponse(
    val Innings: List<Inning>,
    val Matchdetail: Matchdetail,
    val Notes: Notes,
    val Nuggets: List<String>,
    val Teams: List<Team>
)