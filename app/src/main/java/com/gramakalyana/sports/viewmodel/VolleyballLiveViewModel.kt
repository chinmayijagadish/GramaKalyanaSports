package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.VolleyballLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VolleyballLiveViewModel : ViewModel() {

    private val _liveMatch =
        MutableStateFlow(
            VolleyballLiveData()
        )

    val liveMatch: StateFlow<VolleyballLiveData>
        get() = _liveMatch

    fun createLiveMatch(
        volleyballLiveData: VolleyballLiveData
    ) {

        FirebaseManager
            .volleyballLiveRef
            .child(volleyballLiveData.matchId)
            .setValue(volleyballLiveData)
    }

    fun updateLiveMatch(
        volleyballLiveData: VolleyballLiveData
    ) {

        FirebaseManager
            .volleyballLiveRef
            .child(volleyballLiveData.matchId)
            .setValue(volleyballLiveData)
    }

    fun observeLiveMatch(
        matchId: String
    ) {

        FirebaseManager
            .volleyballLiveRef
            .child(matchId)
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val data =
                            snapshot.getValue(
                                VolleyballLiveData::class.java
                            )

                        if (data != null) {

                            _liveMatch.value =
                                data
                        }
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }

    fun finishMatch(
        data: VolleyballLiveData
    ) {

        val winner =

            when {

                data.teamASets >
                        data.teamBSets ->
                    data.teamAName

                data.teamBSets >
                        data.teamASets ->
                    data.teamBName

                else ->
                    "DRAW"
            }

        val updated =

            data.copy(

                winner = winner,

                resultText =
                    "$winner won the match",

                matchCompleted = true,

                matchStatus = "COMPLETED"
            )

        updateLiveMatch(updated)
    }
}