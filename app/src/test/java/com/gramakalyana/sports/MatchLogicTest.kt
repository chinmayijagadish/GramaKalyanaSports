package com.gramakalyana.sports

import org.junit.Assert.assertEquals
import org.junit.Test

class MatchLogicTest {

    @Test
    fun cricketWinnerTest() {

        val teamAScore = 120
        val teamBScore = 100

        val winner =

            if (teamAScore > teamBScore)
                "Team A"
            else
                "Team B"

        assertEquals(
            "Team A",
            winner
        )
    }

    @Test
    fun volleyballWinnerTest() {

        val setsA = 3
        val setsB = 1

        val winner =

            if (setsA > setsB)
                "Team A"
            else
                "Team B"

        assertEquals(
            "Team A",
            winner
        )
    }

    @Test
    fun kabaddiWinnerTest() {

        val scoreA = 42
        val scoreB = 39

        val winner =

            if (scoreA > scoreB)
                "Team A"
            else
                "Team B"

        assertEquals(
            "Team A",
            winner
        )
    }
}