package com.gramakalyana.sports.data.model

data class CricketLiveData(

    val matchId: String = "",

    val battingTeamId: String = "",

    val bowlingTeamId: String = "",

    val battingTeamName: String = "",

    val bowlingTeamName: String = "",

    // INNINGS

    val innings: Int = 1,

    val secondInnings: Boolean = false,

    val inningsCompleted: Boolean = false,

    val firstInningsScore: Int = 0,

    // SCORE

    val runs: Int = 0,

    val wickets: Int = 0,

    val extras: Int = 0,

    val fours: Int = 0,

    val sixes: Int = 0,

    // BALLS + OVERS

    val balls: Int = 0,

    val legalBalls: Int = 0,

    val overs: String = "0.0",

    val maxOvers: Int = 20,

    // TARGET

    val target: Int = 0,

    // PLAYERS

    val strikerName: String = "",

    val nonStrikerName: String = "",

    val bowlerName: String = "",

    // PLAYER SCORES

    val strikerRuns: Int = 0,

    val strikerBalls: Int = 0,

    val nonStrikerRuns: Int = 0,

    val nonStrikerBalls: Int = 0,

    // BOWLER STATS

    val bowlerRuns: Int = 0,

    val bowlerWickets: Int = 0,

    val bowlerBalls: Int = 0,

    // MATCH STATS

    val currentRunRate: Double = 0.0,

    val requiredRunRate: Double = 0.0,

    // OVER DETAILS

    val thisOver: List<String> = emptyList(),

    val recentWickets: List<String> = emptyList(),

    // PARTNERSHIP

    val partnershipRuns: Int = 0,

    // RESULT

    val winner: String = "",

    val resultText: String = "",

    // STATUS

    val matchStatus: String = "LIVE",

    val matchCompleted: Boolean = false
)