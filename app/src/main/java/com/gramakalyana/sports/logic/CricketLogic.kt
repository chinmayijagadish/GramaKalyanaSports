package com.gramakalyana.sports.logic

data class CricketState(

    val runs: Int = 0,

    val wickets: Int = 0,

    val balls: Int = 0,

    val overs: String = "0.0",

    val currentRunRate: Double = 0.0,

    val thisOver: List<String> = emptyList()
)

class CricketLogic {

    private var runs = 0

    private var wickets = 0

    private var balls = 0

    private val thisOverBalls =
        mutableListOf<String>()

    fun addRuns(
        run: Int
    ): CricketState {

        runs += run

        balls++

        thisOverBalls.add(run.toString())

        trimOver()

        return getState()
    }

    fun addWide(): CricketState {

        runs += 1

        thisOverBalls.add("Wd")

        trimOver()

        return getState()
    }

    fun addNoBall(): CricketState {

        runs += 1

        thisOverBalls.add("Nb")

        trimOver()

        return getState()
    }

    fun addBye(): CricketState {

        runs += 1

        balls++

        thisOverBalls.add("B")

        trimOver()

        return getState()
    }

    fun addLegBye(): CricketState {

        runs += 1

        balls++

        thisOverBalls.add("Lb")

        trimOver()

        return getState()
    }

    fun addWicket(): CricketState {

        wickets++

        balls++

        thisOverBalls.add("W")

        trimOver()

        return getState()
    }

    private fun trimOver() {

        if (thisOverBalls.size > 6) {

            thisOverBalls.removeAt(0)
        }
    }

    private fun getOvers(): String {

        val over =
            balls / 6

        val remainingBalls =
            balls % 6

        return "$over.$remainingBalls"
    }

    private fun calculateCRR(): Double {

        if (balls == 0) return 0.0

        val oversPlayed =
            balls / 6.0

        return String.format(
            "%.2f",
            runs / oversPlayed
        ).toDouble()
    }

    fun getState(): CricketState {

        return CricketState(

            runs = runs,

            wickets = wickets,

            balls = balls,

            overs = getOvers(),

            currentRunRate = calculateCRR(),

            thisOver = thisOverBalls.toList()
        )
    }
}