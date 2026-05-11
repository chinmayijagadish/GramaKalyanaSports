package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Tournament
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TournamentViewModel : ViewModel() {

    private val _tournaments =
        MutableStateFlow<List<Tournament>>(emptyList())

    val tournaments: StateFlow<List<Tournament>>
        get() = _tournaments

    init {

        fetchTournaments()
    }

    fun createTournament(
        tournament: Tournament
    ) {

        FirebaseManager
            .tournamentsRef
            .child(tournament.tournamentId)
            .setValue(tournament)
    }

    fun deleteTournament(
        tournamentId: String
    ) {

        FirebaseManager
            .tournamentsRef
            .child(tournamentId)
            .removeValue()
    }

    private fun fetchTournaments() {

        FirebaseManager
            .tournamentsRef
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val tournamentList =
                            mutableListOf<Tournament>()

                        for (tournamentSnapshot in snapshot.children) {

                            val tournament =
                                tournamentSnapshot.getValue(
                                    Tournament::class.java
                                )

                            if (tournament != null) {

                                tournamentList.add(
                                    tournament
                                )
                            }
                        }

                        _tournaments.value =
                            tournamentList
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }
}