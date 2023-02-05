package com.example.sportsinteractive2023.models

data class Player(
    val Batting: Batting?,
    val Bowling: Bowling?,
    val Iskeeper: Boolean?,
    val Iscaptain:Boolean?,
    val Name_Full: String?,
    val Position: String?
){
    fun getPlayerType():String{
        var playerType = ""
        if(Iscaptain!=null && Iscaptain){
            playerType = "C"
            if(Iskeeper!=null && Iskeeper){
                playerType += " & WK"
            }
        }else if(Iskeeper!=null && Iskeeper){
            playerType += "WK"
        }
        return playerType
    }
}