package com.example.sportsinteractive2023.models

import com.google.gson.annotations.SerializedName

data class Notes(
    @SerializedName("1")
    val first:List<String>,
    @SerializedName("2")
    val second:List<String>
)
