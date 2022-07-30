package com.example.pushmessagetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pushmessagetestapp.presentation.navigation.mainGraph
import com.example.pushmessagetestapp.presentation.ui.theme.PushMessageTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PushMessageTestAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "mainGraph"
                ) {
                    mainGraph(navController)
                }
            }
        }
    }
}