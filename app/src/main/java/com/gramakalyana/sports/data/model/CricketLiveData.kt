package com.gramakalyana.sports.data.model

data class CricketLiveData(

    val matchId: String = "",

    val battingTeamId: String = "",

    val bowlingTeamId: String = "",

    val battingTeamName: String = "",

    val bowlingTeamName: String = "",

    val innings: Int = 1,

    val runs: Int = 0,

    val wickets: Int = 0,

    val balls: Int = 0,

    val overs: String = "0.0",

    val target: Int = 0,

    val strikerName: String = "",

    val nonStrikerName: String = "",

    val bowlerName: String = "",

    val currentRunRate: Double = 0.0,

    val requiredRunRate: Double = 0.0,

    val extras: Int = 0,

    val fours: Int = 0,

    val sixes: Int = 0,

    val thisOver: List<String> = emptyList(),

    val recentWickets: List<String> = emptyList(),

    val partnershipRuns: Int = 0,

    val matchStatus: String = "LIVE"
)