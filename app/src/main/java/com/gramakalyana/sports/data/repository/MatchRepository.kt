package com.gramakalyana.sports.data.repository

import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Match

class MatchRepository {

    fun addMatch(match: Match) {

        FirebaseManager
            .matchesRef
            .child(match.matchId)
            .setValue(match)
    }
}