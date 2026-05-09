package com.gramakalyana.sports.data.firebase

import com.google.firebase.database.FirebaseDatabase

object FirebaseManager {

    val database =
        FirebaseDatabase.getInstance()

    val matchesRef =
        database.getReference("grama_kalyana_sports/matches")

    val teamsRef =
        database.getReference(
            "grama_kalyana_sports/teams"
        )

    val playersRef =
        database.getReference(
            "grama_kalyana_sports/players"
        )
}