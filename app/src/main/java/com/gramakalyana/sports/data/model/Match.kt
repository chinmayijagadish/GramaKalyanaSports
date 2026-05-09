package com.gramakalyana.sports.data.model

data class Match(

    val matchId: String = "",

    val sportType: String = "",

    val teamA: String = "",

    val teamB: String = "",

    val scoreA: String = "",

    val scoreB: String = "",

    val status: String = "",

    val zone: String = "",

    val venue: String = "",

    val tournamentStage: String = "",

    val isLive: Boolean = false
)