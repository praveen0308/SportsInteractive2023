package com.example.sportsinteractive2023.utils


fun Throwable.identify(): String {
    return if (this is NoConnectivityException) this.message
    else "Something went wrong!!!"
}
