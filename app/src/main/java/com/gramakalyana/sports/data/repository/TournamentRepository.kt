package com.gramakalyana.sports.data.repository

import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Tournament

class TournamentRepository {

    fun createTournament(
        tournament: Tournament
    ) {

        FirebaseManager
            .tournamentsRef
            .child(tournament.tournamentId)
            .setValue(tournament)
    }
}