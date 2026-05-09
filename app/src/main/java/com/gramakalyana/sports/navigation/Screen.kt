package com.gramakalyana.sports.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object ZoneSelection : Screen("zone_selection")
    object LiveMatches : Screen("live_matches")
    object MatchDetails : Screen("match_details/{matchId}") {
        fun createRoute(matchId: String) = "match_details/$matchId"
    }
    object ScorerLogin : Screen("scorer_login")
    object TournamentSetup : Screen("tournament_setup")
    object LiveScoringCricket : Screen("scoring_cricket/{matchId}") {
        fun createRoute(matchId: String) = "scoring_cricket/$matchId"
    }
    object LiveScoringKabaddi : Screen("scoring_kabaddi/{matchId}") {
        fun createRoute(matchId: String) = "scoring_kabaddi/$matchId"
    }
    object LiveScoringVolleyball : Screen("scoring_volleyball/{matchId}") {
        fun createRoute(matchId: String) = "scoring_volleyball/$matchId"
    }
    object PlayerStats : Screen("player_stats/{playerId}") {
        fun createRoute(playerId: String) = "player_stats/$playerId"
    }
}
