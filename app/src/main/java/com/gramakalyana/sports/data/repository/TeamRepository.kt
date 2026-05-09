package com.gramakalyana.sports.data.repository

import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Team

class TeamRepository {

    fun addTeam(team: Team) {

        FirebaseManager
            .teamsRef
            .child(team.teamId)
            .setValue(team)
    }
}