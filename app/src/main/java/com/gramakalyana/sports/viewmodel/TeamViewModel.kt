package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TeamViewModel : ViewModel() {

    private val _teams =
        MutableStateFlow<List<Team>>(emptyList())

    val teams: StateFlow<List<Team>>
        get() = _teams

    init {

        fetchTeams()
    }

    fun createTeam(team: Team) {

        FirebaseManager
            .teamsRef
            .child(team.teamId)
            .setValue(team)
    }

    fun deleteTeam(
        teamId: String
    ) {

        FirebaseManager
            .teamsRef
            .child(teamId)
            .removeValue()
    }

    fun updatePlayerCount(

        teamId: String,

        count: Int
    ) {

        FirebaseManager
            .teamsRef
            .child(teamId)
            .child("playerCount")
            .setValue(count)
    }

    private fun fetchTeams() {

        FirebaseManager
            .teamsRef
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val teamList =
                            mutableListOf<Team>()

                        for (teamSnapshot in snapshot.children) {

                            val team =
                                teamSnapshot.getValue(
                                    Team::class.java
                                )

                            if (team != null) {

                                teamList.add(team)
                            }
                        }

                        _teams.value =
                            teamList
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }
}