package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.CricketLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CricketLiveViewModel : ViewModel() {

    private val _liveMatch =
        MutableStateFlow(
            CricketLiveData()
        )

    val liveMatch: StateFlow<CricketLiveData>
        get() = _liveMatch

    fun createLiveMatch(
        cricketLiveData: CricketLiveData
    ) {

        FirebaseManager
            .cricketLiveRef
            .child(cricketLiveData.matchId)
            .setValue(cricketLiveData)
    }

    fun updateLiveMatch(
        cricketLiveData: CricketLiveData
    ) {

        FirebaseManager
            .cricketLiveRef
            .child(cricketLiveData.matchId)
            .setValue(cricketLiveData)
    }

    fun observeLiveMatch(
        matchId: String
    ) {

        FirebaseManager
            .cricketLiveRef
            .child(matchId)
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val liveData =
                            snapshot.getValue(
                                CricketLiveData::class.java
                            )

                        if (liveData != null) {

                            _liveMatch.value =
                                liveData
                        }
                    }

                    override fun onCancelled(
                        error: DatabaseError
                    ) {

                    }
                }
            )
    }

    fun resetMatch(
        matchId: String
    ) {

        FirebaseManager
            .cricketLiveRef
            .child(matchId)
            .removeValue()
    }
}