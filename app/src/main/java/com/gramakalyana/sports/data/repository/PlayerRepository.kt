package com.gramakalyana.sports.data.repository

import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.Player

class PlayerRepository {

    fun addPlayer(player: Player) {

        FirebaseManager
            .playersRef
            .child(player.playerId)
            .setValue(player)
    }
}