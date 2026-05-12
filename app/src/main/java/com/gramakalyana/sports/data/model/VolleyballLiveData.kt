package com.gramakalyana.sports.data.model

data class VolleyballLiveData(

    val matchId: String = "",

    val teamAPoints: Int = 0,

    val teamBPoints: Int = 0,

    val teamASets: Int = 0,

    val teamBSets: Int = 0,

    val currentSet: Int = 1,

    val servingTeam: String = "",

    val timeoutA: Int = 0,

    val timeoutB: Int = 0,

    val matchPoint: Boolean = false,

    val matchStatus: String = "LIVE"
)