package com.gramakalyana.sports.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.gramakalyana.sports.navigation.Screen
import com.gramakalyana.sports.ui.components.GlassmorphismCard
import com.gramakalyana.sports.ui.components.GradientBackground

@Composable
fun ScorerLoginScreen(navController: NavController) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val auth = FirebaseAuth.getInstance()

    val scrollState = rememberScrollState()

    GradientBackground {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 32.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                imageVector = Icons.Default.Person,

                contentDescription = "Admin Scorer",

                modifier = Modifier.size(80.dp),

                tint = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Scorer Portal",

                style = MaterialTheme.typography.headlineMedium,

                color = Color.White,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Authorized Personnel Only",

                color = Color.White.copy(alpha = 0.8f),

                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(32.dp))

            GlassmorphismCard(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),

                    horizontalAlignment = Alignment.CenterHorizontally,

                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {

                    OutlinedTextField(
                        value = email,

                        onValueChange = {
                            email = it
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {
                            Text(
                                "Email Address",
                                color = Color.White
                            )
                        },

                        leadingIcon = {

                            Icon(
                                imageVector = Icons.Default.Email,

                                contentDescription = null,

                                tint = Color.White
                            )
                        },

                        singleLine = true,

                        shape = RoundedCornerShape(12.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,

                            unfocusedTextColor = Color.White,

                            focusedBorderColor =
                                MaterialTheme.colorScheme.primary,

                            unfocusedBorderColor =
                                Color.White.copy(alpha = 0.5f)
                        )
                    )

                    OutlinedTextField(
                        value = password,

                        onValueChange = {
                            password = it
                        },

                        modifier = Modifier.fillMaxWidth(),

                        label = {
                            Text(
                                "Password",
                                color = Color.White
                            )
                        },

                        leadingIcon = {

                            Icon(
                                imageVector = Icons.Default.Lock,

                                contentDescription = null,

                                tint = Color.White
                            )
                        },

                        visualTransformation =
                            PasswordVisualTransformation(),

                        singleLine = true,

                        shape = RoundedCornerShape(12.dp),

                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,

                            unfocusedTextColor = Color.White,

                            focusedBorderColor =
                                MaterialTheme.colorScheme.primary,

                            unfocusedBorderColor =
                                Color.White.copy(alpha = 0.5f)
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(

                        onClick = {

                            if (
                                email.isBlank() ||
                                password.isBlank()
                            ) {

                                Toast.makeText(
                                    navController.context,
                                    "Please enter email and password",
                                    Toast.LENGTH_SHORT
                                ).show()

                                return@Button
                            }

                            isLoading = true

                            auth.signInWithEmailAndPassword(
                                email,
                                password
                            )

                                .addOnCompleteListener { task ->

                                    isLoading = false

                                    if (task.isSuccessful) {

                                        Toast.makeText(
                                            navController.context,
                                            "Login Successful",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        navController.navigate(
                                            Screen.TournamentSetup.route
                                        ) {

                                            popUpTo(
                                                Screen.ScorerLogin.route
                                            ) {
                                                inclusive = true
                                            }
                                        }

                                    } else {

                                        Toast.makeText(
                                            navController.context,

                                            task.exception?.message
                                                ?: "Login Failed",

                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),

                        shape = RoundedCornerShape(14.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.primary
                        )
                    ) {

                        Text(
                            text =
                                if (isLoading)
                                    "LOGGING IN..."
                                else
                                    "LOGIN",

                            fontWeight = FontWeight.Bold,

                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}