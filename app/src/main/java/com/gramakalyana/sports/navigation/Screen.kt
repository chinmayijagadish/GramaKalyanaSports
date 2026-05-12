package com.gramakalyana.sports.navigation

sealed class Screen(val route: String) {

    object Splash : Screen("splash")

    object Home : Screen("home")

    object ZoneSelection :
        Screen("zone_selection")

    object LiveMatches :
        Screen("live_matches")

    object ScorerLogin :
        Screen("scorer_login")

    object TournamentSetup :
        Screen("tournament_setup")

    object TournamentHome :
        Screen("tournament_home")

    object MatchDetails :
        Screen("match_details/{matchId}") {

        fun createRoute(
            matchId: String
        ) = "match_details/$matchId"
    }

    object LiveScoringCricket :
        Screen("scoring_cricket/{matchId}") {

        fun createRoute(
            matchId: String
        ) = "scoring_cricket/$matchId"
    }

    object LiveScoringKabaddi :
        Screen("scoring_kabaddi/{matchId}") {

        fun createRoute(
            matchId: String
        ) = "scoring_kabaddi/$matchId"
    }

    object LiveScoringVolleyball :
        Screen("scoring_volleyball/{matchId}") {

        fun createRoute(
            matchId: String
        ) = "scoring_volleyball/$matchId"
    }

    object PlayerStats :
        Screen("player_stats/{playerId}") {

        fun createRoute(
            playerId: String
        ) = "player_stats/$playerId"
    }

    object AddTeam :
        Screen(
            "add_team/{tournamentId}/{sport}/{zone}"
        ) {

        fun createRoute(
            tournamentId: String,
            sport: String,
            zone: String
        ) =
            "add_team/$tournamentId/$sport/$zone"
    }

    object TournamentDashboard :
        Screen(
            "tournament_dashboard/{tournamentId}/{tournamentName}/{sport}/{zone}"
        ) {

        fun createRoute(

            tournamentId: String,

            tournamentName: String,

            sport: String,

            zone: String

        ) =

            "tournament_dashboard/$tournamentId/$tournamentName/$sport/$zone"
    }

    object TeamPlayers :
        Screen(
            "team_players/{teamId}/{tournamentId}/{sport}"
        ) {

        fun createRoute(
            teamId: String,
            tournamentId: String,
            sport: String
        ) =
            "team_players/$teamId/$tournamentId/$sport"
    }

    object AddPlayer :
        Screen(
            "add_player/{teamId}/{tournamentId}/{sport}"
        ) {

        fun createRoute(
            teamId: String,
            tournamentId: String,
            sport: String
        ) =
            "add_player/$teamId/$tournamentId/$sport"
    }

    object CreateMatch :
        Screen(
            "create_match/{tournamentId}/{sportType}/{zone}"
        ) {

        fun createRoute(
            tournamentId: String,
            sportType: String,
            zone: String
        ) =
            "create_match/$tournamentId/$sportType/$zone"
    }

    object LiveScore :
        Screen(
            "live_score/{matchId}"
        ) {

        fun createRoute(
            matchId: String
        ) =
            "live_score/$matchId"
    }
}