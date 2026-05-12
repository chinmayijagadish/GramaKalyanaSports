package com.gramakalyana.sports.data.model

data class Match(

    val matchId: String = "",

    val tournamentId: String = "",

    val tournamentName: String = "",

    val zone: String = "",

    val sportType: String = "",

    val teamAId: String = "",

    val teamAName: String = "",

    val teamBId: String = "",

    val teamBName: String = "",

    val matchDate: String = "",

    val matchTime: String = "",

    val venue: String = "",

    val status: String = "UPCOMING",

    val currentPhase: String = "",

    val winner: String = "",

    val tossWinner: String = "",

    val tossDecision: String = "",

    val matchNotes: String = "",

    // TEMP SCORE SUPPORT
    val teamAScore: Int = 0,

    val teamBScore: Int = 0
)