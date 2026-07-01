package com.tnyx.shared.profile.domain.model

/**
 * Domain model for user profile data.
 * 
 * Pure Kotlin, no Android dependencies.
 * Platform-agnostic and testable.
 */
data class ProfileData(
    // User Info
    val displayName: String,
    val planLabel: String,
    val weight: Double,
    val height: Int,
    val bmi: Double,
    val bmr: Int,
    
    // Stats Grid
    val workoutStats: WorkoutStats,
    val calendarStats: CalendarStats,
    val overallStats: OverallStats,
    val exerciseStats: ExerciseStats,
    
    // Journey
    val journey: Journey,
    
    // Progress Photos
    val progressPhotos: List<String>
)

data class WorkoutStats(
    val weeklyCount: Int,
    val currentStreak: Int
)

data class CalendarStats(
    val nextWorkoutName: String,
    val nextWorkoutDate: String
)

data class OverallStats(
    val totalWorkouts: Int,
    val totalCalories: Int
)

data class ExerciseStats(
    val totalExercises: Int,
    val favoriteCount: Int
)

data class Journey(
    val initialWeight: Double,
    val currentWeight: Double,
    val targetWeight: Double,
    val progressPercent: Float
)
