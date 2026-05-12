package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Match
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MatchViewModel : ViewModel() {

    private val _matches =
        MutableStateFlow<List<Match>>(emptyList())

    val matches: StateFlow<List<Match>>
        get() = _matches

    init {

        fetchMatches()
    }

    fun createMatch(
        match: Match
    ) {

        FirebaseManager
            .matchesRef
            .child(match.matchId)
            .setValue(match)
    }

    fun updateMatch(
        match: Match
    ) {

        FirebaseManager
            .matchesRef
            .child(match.matchId)
            .setValue(match)
    }

    fun deleteMatch(
        matchId: String
    ) {

        FirebaseManager
            .matchesRef
            .child(matchId)
            .removeValue()

        FirebaseManager
            .cricketLiveRef
            .child(matchId)
            .removeValue()

        FirebaseManager
            .kabaddiLiveRef
            .child(matchId)
            .removeValue()

        FirebaseManager
            .volleyballLiveRef
            .child(matchId)
            .removeValue()
    }

    fun updateMatchStatus(

        matchId: String,

        status: String
    ) {

        FirebaseManager
            .matchesRef
            .child(matchId)
            .child("status")
            .setValue(status)
    }

    fun finishMatch(

        matchId: String,

        winner: String
    ) {

        FirebaseManager
            .matchesRef
            .child(matchId)
            .child("status")
            .setValue("COMPLETED")

        FirebaseManager
            .matchesRef
            .child(matchId)
            .child("winner")
            .setValue(winner)
    }

    private fun fetchMatches() {

        FirebaseManager
            .matchesRef
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val matchList =
                            mutableListOf<Match>()

                        for (matchSnapshot in snapshot.children) {

                            val match =
                                matchSnapshot.getValue(
                                    Match::class.java
                                )

                            if (match != null) {

                                matchList.add(match)
                            }
                        }

                        _matches.value =
                            matchList
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }
}