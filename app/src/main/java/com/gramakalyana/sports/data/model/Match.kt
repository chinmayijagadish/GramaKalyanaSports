package com.gramakalyana.sports.data.model

data class Match(

    val matchId: String = "",

    val tournamentId: String = "",

    val teamAId: String = "",

    val teamAName: String = "",

    val teamBId: String = "",

    val teamBName: String = "",

    val sportType: String = "",

    val matchDate: String = "",

    val matchTime: String = "",

    val venue: String = "",

    val status: String = "Upcoming",

    val teamAScore: Int = 0,

    val teamBScore: Int = 0,

    val winner: String = "",

    val oversPlayed: String = "",

    val matchNotes: String = ""
)