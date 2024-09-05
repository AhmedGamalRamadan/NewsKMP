package com.ag.project.utils


sealed class Screen(val rout: String) {
    data object Home : Screen("home")
    data object Details : Screen("details")
}