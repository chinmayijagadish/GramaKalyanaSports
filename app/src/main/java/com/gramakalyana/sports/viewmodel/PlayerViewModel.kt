package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Player
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PlayerViewModel : ViewModel() {

    private val _players =
        MutableStateFlow<List<Player>>(emptyList())

    val players: StateFlow<List<Player>>
        get() = _players

    init {

        fetchPlayers()
    }

    fun createPlayer(
        player: Player
    ) {

        FirebaseManager
            .playersRef
            .child(player.playerId)
            .setValue(player)
    }

    fun deletePlayer(
        playerId: String
    ) {

        FirebaseManager
            .playersRef
            .child(playerId)
            .removeValue()
    }

    private fun fetchPlayers() {

        FirebaseManager
            .playersRef
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val playerList =
                            mutableListOf<Player>()

                        for (playerSnapshot in snapshot.children) {

                            val player =
                                playerSnapshot.getValue(
                                    Player::class.java
                                )

                            if (player != null) {

                                playerList.add(player)
                            }
                        }

                        _players.value =
                            playerList
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }
}