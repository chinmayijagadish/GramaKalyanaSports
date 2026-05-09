package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.gramakalyana.sports.data.model.Player
import com.gramakalyana.sports.data.repository.PlayerRepository

class PlayerViewModel : ViewModel() {

    private val repository =
        PlayerRepository()

    fun createPlayer(player: Player) {

        repository.addPlayer(player)
    }
}