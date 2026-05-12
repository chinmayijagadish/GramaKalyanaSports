package com.gramakalyana.sports.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.gramakalyana.sports.data.firebase.FirebaseManager
import com.gramakalyana.sports.data.model.KabaddiLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class KabaddiLiveViewModel : ViewModel() {

    private val _liveMatch =
        MutableStateFlow(
            KabaddiLiveData()
        )

    val liveMatch: StateFlow<KabaddiLiveData>
        get() = _liveMatch

    fun createLiveMatch(
        kabaddiLiveData: KabaddiLiveData
    ) {

        FirebaseManager
            .kabaddiLiveRef
            .child(kabaddiLiveData.matchId)
            .setValue(kabaddiLiveData)
    }

    fun updateLiveMatch(
        kabaddiLiveData: KabaddiLiveData
    ) {

        FirebaseManager
            .kabaddiLiveRef
            .child(kabaddiLiveData.matchId)
            .setValue(kabaddiLiveData)
    }

    fun observeLiveMatch(
        matchId: String
    ) {

        FirebaseManager
            .kabaddiLiveRef
            .child(matchId)
            .addValueEventListener(

                object : ValueEventListener {

                    override fun onDataChange(
                        snapshot: DataSnapshot
                    ) {

                        val data =
                            snapshot.getValue(
                                KabaddiLiveData::class.java
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
}