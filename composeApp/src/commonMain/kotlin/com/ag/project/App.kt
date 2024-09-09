package com.ag.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.ag.project.presentation.navigation.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigation()

    }
}