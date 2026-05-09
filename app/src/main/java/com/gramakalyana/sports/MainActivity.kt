package com.gramakalyana.sports

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp
import com.gramakalyana.sports.navigation.AppNavGraph
import com.gramakalyana.sports.ui.theme.GramaKalyanaSportsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        try {

            FirebaseApp.initializeApp(this)

            Log.d("FIREBASE", "Firebase initialized successfully")

        } catch (e: Exception) {

            Log.e("FIREBASE", "Firebase init failed: ${e.message}")
        }

        setContent {

            GramaKalyanaSportsTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}