package com.gramakalyana.sports.data.model

data class KabaddiLiveData(

    val matchId: String = "",

    val teamAName: String = "Team A",

    val teamBName: String = "Team B",

    val teamAScore: Int = 0,

    val teamBScore: Int = 0,

    val currentHalf: Int = 1,

    val raidTimeLeft: Int = 30,

    val currentRaidingTeam: String = "A",

    val teamAPlayersOnMat: Int = 7,

    val teamBPlayersOnMat: Int = 7,

    val superRaid: Boolean = false,

    val superTackle: Boolean = false,

    val allOutCountA: Int = 0,

    val allOutCountB: Int = 0,

    val bonusPointsA: Int = 0,

    val bonusPointsB: Int = 0,

    val tacklePointsA: Int = 0,

    val tacklePointsB: Int = 0,

    val touchPointsA: Int = 0,

    val touchPointsB: Int = 0,

    val recentRaids: List<String> = emptyList(),

    val matchCompleted: Boolean = false,

    val winner: String = "",

    val resultText: String = "",

    val matchStatus: String = "LIVE"
)