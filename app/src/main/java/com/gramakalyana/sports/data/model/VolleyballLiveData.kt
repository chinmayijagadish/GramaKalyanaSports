package com.gramakalyana.sports.data.model

data class VolleyballLiveData(

    val matchId: String = "",

    val teamAName: String = "Team A",

    val teamBName: String = "Team B",

    val teamAPoints: Int = 0,

    val teamBPoints: Int = 0,

    val teamASets: Int = 0,

    val teamBSets: Int = 0,

    val currentSet: Int = 1,

    val servingTeam: String = "A",

    val timeoutA: Int = 2,

    val timeoutB: Int = 2,

    val matchPoint: Boolean = false,

    val setResults: List<String> = emptyList(),

    val recentPoints: List<String> = emptyList(),

    val matchCompleted: Boolean = false,

    val winner: String = "",

    val resultText: String = "",

    val matchStatus: String = "LIVE"
)