package com.gramakalyana.sports.logic

data class VolleyballState(

    val teamAPoints: Int = 0,

    val teamBPoints: Int = 0,

    val teamASets: Int = 0,

    val teamBSets: Int = 0,

    val currentSet: Int = 1
)

class VolleyballLogic {

    private var teamAPoints = 0

    private var teamBPoints = 0

    private var teamASets = 0

    private var teamBSets = 0

    private var currentSet = 1

    fun addTeamAPoint(): VolleyballState {

        teamAPoints++

        return getState()
    }

    fun addTeamBPoint(): VolleyballState {

        teamBPoints++

        return getState()
    }

    fun endSet(): VolleyballState {

        if (teamAPoints > teamBPoints) {

            teamASets++

        } else if (teamBPoints > teamAPoints) {

            teamBSets++
        }

        currentSet++

        teamAPoints = 0

        teamBPoints = 0

        return getState()
    }

    private fun getState(): VolleyballState {

        return VolleyballState(

            teamAPoints = teamAPoints,

            teamBPoints = teamBPoints,

            teamASets = teamASets,

            teamBSets = teamBSets,

            currentSet = currentSet
        )
    }
}