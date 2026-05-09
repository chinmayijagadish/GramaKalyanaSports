package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.gramakalyana.sports.data.model.Team
import com.gramakalyana.sports.data.repository.TeamRepository

class TeamViewModel : ViewModel() {

    private val repository =
        TeamRepository()

    fun createTeam(team: Team) {

        repository.addTeam(team)
    }
}