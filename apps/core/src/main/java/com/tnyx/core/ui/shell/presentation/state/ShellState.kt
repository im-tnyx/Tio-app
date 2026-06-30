package com.tnyx.core.ui.shell.presentation.state

import androidx.compose.runtime.Immutable

/**
 * Tabs available in the app shell.
 */
enum class ShellTab {
    Home,
    Nutrition,
    Ai,
    Workout,
    Progress
}

enum class ShellPlanTier {
    Free,
    Plus,
    Premium
}

@Immutable
data class ShellUiState(
    val selectedTab: ShellTab = ShellTab.Home,
    val isBottomNavVisible: Boolean = true,
    val appBarOpacity: Float = 0f,
    val planTier: ShellPlanTier = ShellPlanTier.Free
)
