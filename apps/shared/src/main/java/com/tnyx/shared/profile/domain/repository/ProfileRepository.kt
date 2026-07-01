package com.tnyx.shared.profile.domain.repository

import com.tnyx.shared.profile.domain.model.ProfileData

/**
 * Profile repository contract.
 * 
 * Platform-agnostic interface for profile data access.
 * Implementations live in platform-specific layers (:app data layer for Android).
 */
interface ProfileRepository {
    /**
     * Get current user profile data.
     * 
     * @return ProfileData with user info, stats, journey, and photos
     */
    suspend fun getProfileData(): Result<ProfileData>
}
