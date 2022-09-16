package com.example.pushmessagetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.pushmessagetestapp.presentation.navigation.Graphs
import com.example.pushmessagetestapp.presentation.navigation.graph.mainGraph
import com.example.pushmessagetestapp.presentation.ui.theme.PushMessageTestAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PushMessageTestAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Graphs.MainGraph.graphName,
                    ) {
                        mainGraph(navController, Graphs.MainGraph.graphName)
                    }
                }
            }
        }
    }
}