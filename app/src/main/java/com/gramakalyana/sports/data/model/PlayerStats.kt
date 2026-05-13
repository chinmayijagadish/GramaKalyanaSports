package com.gramakalyana.sports.data.model

data class PlayerStats(

    val playerId: String = "",

    val playerName: String = "",

    val matchesPlayed: Int = 0,

    // CRICKET

    val totalRuns: Int = 0,

    val totalWickets: Int = 0,

    val totalFours: Int = 0,

    val totalSixes: Int = 0,

    // KABADDI

    val totalRaidPoints: Int = 0,

    val totalTacklePoints: Int = 0,

    // VOLLEYBALL

    val totalSmashes: Int = 0,

    val totalBlocks: Int = 0,

    // COMMON

    val mvpAwards: Int = 0
)