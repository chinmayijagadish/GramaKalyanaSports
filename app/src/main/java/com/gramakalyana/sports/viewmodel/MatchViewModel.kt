package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.gramakalyana.sports.data.model.Match
import com.gramakalyana.sports.data.repository.MatchRepository

class MatchViewModel : ViewModel() {

    private val repository = MatchRepository()

    fun createMatch(match: Match) {

        repository.addMatch(match)
    }
}