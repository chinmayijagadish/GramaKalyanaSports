package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.PlayerStats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayerStatsViewModel : ViewModel() {

    private val _playerStats =

        MutableStateFlow(
            PlayerStats()
        )

    val playerStats:
            StateFlow<PlayerStats>
        get() = _playerStats

    fun observePlayerStats(
        playerId: String
    ) {

        FirebaseManager
            .playerStatsRef
            .child(playerId)
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val stats =
                            snapshot.getValue(
                                PlayerStats::class.java
                            )

                        if (stats != null) {

                            _playerStats.value =
                                stats
                        }
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }

    fun updatePlayerStats(
        stats: PlayerStats
    ) {

        FirebaseManager
            .playerStatsRef
            .child(stats.playerId)
            .setValue(stats)
    }
}