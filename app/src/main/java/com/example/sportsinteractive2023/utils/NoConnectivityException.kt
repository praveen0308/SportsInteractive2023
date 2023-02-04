package com.example.sportsinteractive2023.utils
import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No internet!!!"
}