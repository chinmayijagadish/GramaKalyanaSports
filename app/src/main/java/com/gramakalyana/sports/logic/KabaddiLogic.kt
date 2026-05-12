package com.gramakalyana.sports.logic

data class KabaddiState(

    val teamAScore: Int = 0,

    val teamBScore: Int = 0
)

class KabaddiLogic {

    private var teamAScore = 0

    private var teamBScore = 0

    fun addTeamATouch(): KabaddiState {

        teamAScore += 1

        return getState()
    }

    fun addTeamABonus(): KabaddiState {

        teamAScore += 1

        return getState()
    }

    fun addTeamATackle(): KabaddiState {

        teamAScore += 1

        return getState()
    }

    fun addTeamAAllOut(): KabaddiState {

        teamAScore += 2

        return getState()
    }

    fun addTeamBTouch(): KabaddiState {

        teamBScore += 1

        return getState()
    }

    fun addTeamBBonus(): KabaddiState {

        teamBScore += 1

        return getState()
    }

    fun addTeamBTackle(): KabaddiState {

        teamBScore += 1

        return getState()
    }

    fun addTeamBAllOut(): KabaddiState {

        teamBScore += 2

        return getState()
    }

    private fun getState(): KabaddiState {

        return KabaddiState(

            teamAScore = teamAScore,

            teamBScore = teamBScore
        )
    }
}