package com.gramakalyana.sports.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gramakalyana.sports.ui.screens.auth.ScorerLoginScreen
import com.gramakalyana.sports.ui.screens.home.HomeScreen
import com.gramakalyana.sports.ui.screens.live.LiveMatchesScreen
import com.gramakalyana.sports.ui.screens.live.MatchDetailsScreen
import com.gramakalyana.sports.ui.screens.match.CreateMatchScreen
import com.gramakalyana.sports.ui.screens.player.AddPlayerScreen
import com.gramakalyana.sports.ui.screens.player.TeamPlayersScreen
import com.gramakalyana.sports.ui.screens.scoring.CricketScoringScreen
import com.gramakalyana.sports.ui.screens.scoring.KabaddiScoringScreen
import com.gramakalyana.sports.ui.screens.scoring.VolleyballScoringScreen
import com.gramakalyana.sports.ui.screens.setup.AddTeamScreen
import com.gramakalyana.sports.ui.screens.setup.TournamentDashboardScreen
import com.gramakalyana.sports.ui.screens.setup.TournamentSetupScreen
import com.gramakalyana.sports.ui.screens.splash.SplashScreen
import com.gramakalyana.sports.ui.screens.stats.PlayerStatsScreen
import com.gramakalyana.sports.ui.screens.tournament.TournamentHomeScreen
import com.gramakalyana.sports.ui.screens.zone.ZoneSelectionScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,

        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(
                navController = navController
            )
        }

        composable(Screen.Home.route) {

            HomeScreen(
                navController = navController
            )
        }

        composable(Screen.ZoneSelection.route) {

            ZoneSelectionScreen(
                navController = navController
            )
        }

        composable(Screen.LiveMatches.route) {

            LiveMatchesScreen(
                navController = navController
            )
        }

        composable(Screen.ScorerLogin.route) {

            ScorerLoginScreen(
                navController = navController
            )
        }

        composable(Screen.TournamentSetup.route) {

            TournamentSetupScreen(
                navController = navController
            )
        }

        composable(Screen.TournamentHome.route) {

            TournamentHomeScreen(
                navController = navController
            )
        }

        composable(Screen.MatchDetails.route) {

                backStackEntry ->

            val matchId =
                backStackEntry.arguments
                    ?.getString("matchId")
                    ?: ""

            MatchDetailsScreen(

                navController = navController,

                matchId = matchId
            )
        }

        // CRICKET SCORING

        composable(
            Screen.LiveScoringCricket.route
        ) {

                backStackEntry ->

            val matchId =
                backStackEntry.arguments
                    ?.getString("matchId")

            CricketScoringScreen(

                navController = navController,

                matchId = matchId
            )
        }

        // KABADDI SCORING

        composable(
            Screen.LiveScoringKabaddi.route
        ) {

                backStackEntry ->

            val matchId =
                backStackEntry.arguments
                    ?.getString("matchId")

            KabaddiScoringScreen(

                navController = navController,

                matchId = matchId
            )
        }

        // VOLLEYBALL SCORING

        composable(
            Screen.LiveScoringVolleyball.route
        ) {

                backStackEntry ->

            val matchId =
                backStackEntry.arguments
                    ?.getString("matchId")

            VolleyballScoringScreen(

                navController = navController,

                matchId = matchId
            )
        }

        composable(Screen.PlayerStats.route) {

                backStackEntry ->

            val playerId =
                backStackEntry.arguments
                    ?.getString("playerId")
                    ?: ""

            PlayerStatsScreen(
                playerId = playerId
            )
        }

        composable(
            Screen.AddTeam.route
        ) {

                backStackEntry ->

            val tournamentId =
                backStackEntry.arguments
                    ?.getString("tournamentId")
                    ?: ""

            val sport =
                backStackEntry.arguments
                    ?.getString("sport")
                    ?: ""

            val zone =
                backStackEntry.arguments
                    ?.getString("zone")
                    ?: ""

            AddTeamScreen(

                navController = navController,

                tournamentId = tournamentId,

                selectedSport = sport,

                selectedZone = zone
            )
        }

        composable(
            Screen.TournamentDashboard.route
        ) {

                backStackEntry ->

            val tournamentId =
                backStackEntry.arguments
                    ?.getString("tournamentId")
                    ?: ""

            val tournamentName =
                backStackEntry.arguments
                    ?.getString("tournamentName")
                    ?: ""

            val sport =
                backStackEntry.arguments
                    ?.getString("sport")
                    ?: ""

            val zone =
                backStackEntry.arguments
                    ?.getString("zone")
                    ?: ""

            TournamentDashboardScreen(

                navController = navController,

                tournamentId = tournamentId,

                tournamentName = tournamentName,

                sport = sport,

                zone = zone
            )
        }

        composable(
            Screen.TeamPlayers.route
        ) {

                backStackEntry ->

            val teamId =
                backStackEntry.arguments
                    ?.getString("teamId")
                    ?: ""

            val tournamentId =
                backStackEntry.arguments
                    ?.getString("tournamentId")
                    ?: ""

            val sport =
                backStackEntry.arguments
                    ?.getString("sport")
                    ?: ""

            TeamPlayersScreen(

                navController = navController,

                teamId = teamId,

                tournamentId = tournamentId,

                sportType = sport
            )
        }

        composable(
            Screen.AddPlayer.route
        ) {

                backStackEntry ->

            val teamId =
                backStackEntry.arguments
                    ?.getString("teamId")
                    ?: ""

            val tournamentId =
                backStackEntry.arguments
                    ?.getString("tournamentId")
                    ?: ""

            val sport =
                backStackEntry.arguments
                    ?.getString("sport")
                    ?: ""

            AddPlayerScreen(

                navController = navController,

                teamId = teamId,

                tournamentId = tournamentId,

                sportType = sport
            )
        }

        composable(
            Screen.CreateMatch.route
        ) {

                backStackEntry ->

            val tournamentId =
                backStackEntry.arguments
                    ?.getString("tournamentId")
                    ?: ""

            val sportType =
                backStackEntry.arguments
                    ?.getString("sportType")
                    ?: ""

            val zone =
                backStackEntry.arguments
                    ?.getString("zone")
                    ?: ""

            CreateMatchScreen(

                navController = navController,

                tournamentId = tournamentId,

                sportType = sportType,

                zone = zone
            )
        }
    }
}