package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.gramakalyana.sports.data.model.Tournament
import com.gramakalyana.sports.data.repository.TournamentRepository

class TournamentViewModel : ViewModel() {

    private val repository =
        TournamentRepository()

    fun createTournament(
        tournament: Tournament
    ) {

        repository.createTournament(
            tournament
        )
    }
}