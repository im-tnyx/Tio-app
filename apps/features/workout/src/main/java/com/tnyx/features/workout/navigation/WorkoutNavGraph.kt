package com.tnyx.features.workout.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.tnyx.routing.routes.MainRoute
import kotlinx.serialization.Serializable

/**
 * Workout feature graph.
 *
 * Workout is intentionally kept as a simple placeholder until the next product
 * redesign defines the real screen structure.
 */
fun NavGraphBuilder.workoutGraph(
    @Suppress("UNUSED_PARAMETER")
    navController: NavHostController
) {
    navigation<MainRoute.WorkoutGraph>(
        startDestination = WorkoutPlaceholder
    ) {
        composable<WorkoutPlaceholder> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Workout")
            }
        }
    }
}

@Serializable
private data object WorkoutPlaceholder
