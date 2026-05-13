package com.gramakalyana.sports.data.firebase

import com.google.firebase.database.FirebaseDatabase

object FirebaseManager {

    private val database =
        FirebaseDatabase.getInstance(
            "https://gramakalyana-4575a-default-rtdb.firebaseio.com/"
        )

    val tournamentsRef =
        database.getReference(
            "grama_kalyana_sports/tournaments"
        )

    val matchesRef =
        database.getReference(
            "grama_kalyana_sports/matches"
        )

    val teamsRef =
        database.getReference(
            "grama_kalyana_sports/teams"
        )

    val playersRef =
        database.getReference(
            "grama_kalyana_sports/players"
        )

    val cricketLiveRef =
        database.getReference(
            "grama_kalyana_sports/cricket_live"
        )

    val kabaddiLiveRef =
        database.getReference(
            "grama_kalyana_sports/kabaddi_live"
        )

    val volleyballLiveRef =
        database.getReference(
            "grama_kalyana_sports/volleyball_live"
        )

    val playerStatsRef =
        database.getReference(
            "player_stats"
        )
}