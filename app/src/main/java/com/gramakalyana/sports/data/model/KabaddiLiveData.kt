package com.gramakalyana.sports.data.model

data class KabaddiLiveData(

    val matchId: String = "",

    val teamAScore: Int = 0,

    val teamBScore: Int = 0,

    val currentHalf: Int = 1,

    val raidTimeLeft: Int = 30,

    val teamAPlayersOnMat: Int = 7,

    val teamBPlayersOnMat: Int = 7,

    val superRaid: Boolean = false,

    val superTackle: Boolean = false,

    val allOutCountA: Int = 0,

    val allOutCountB: Int = 0,

    val matchStatus: String = "LIVE"
)