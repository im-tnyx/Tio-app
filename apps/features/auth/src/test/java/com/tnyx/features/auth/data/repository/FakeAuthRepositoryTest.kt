package com.tnyx.features.auth.data.repository

import com.tnyx.features.auth.domain.model.AuthResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeAuthRepositoryTest {
    private val repository = FakeAuthRepository()

    @Test
    fun signIn_withValidCredentials_returnsAuthenticated() = runTest {
        val email = "test@example.com"
        val result = repository.signIn(email, "password123")
        assertTrue(result is AuthResult.Authenticated)
        val session = (result as AuthResult.Authenticated).session
        assertEquals(email, session.email)
        assertEquals("fake-user-${email.hashCode()}", session.userId)
        assertEquals("Test", session.displayName)
        assertEquals(false, session.isDemo)
    }

    @Test
    fun signIn_withShortPassword_returnsFailure() = runTest {
        val result = repository.signIn("test@example.com", "12345")
        assertTrue(result is AuthResult.Failure)
        assertEquals("Password must be at least 6 characters", (result as AuthResult.Failure).message)
    }

    @Test
    fun signInWithDemoAccount_returnsAuthenticatedDemoUser() = runTest {
        val result = repository.signInWithDemoAccount()
        assertTrue(result is AuthResult.Authenticated)
        val session = (result as AuthResult.Authenticated).session
        assertEquals("demo@tnyx.app", session.email)
        assertEquals("demo-user", session.userId)
        assertEquals("Demo User", session.displayName)
        assertEquals(true, session.isDemo)
    }

    @Test
    fun signUp_withValidDetails_returnsVerificationRequired() = runTest {
        val email = "newuser@example.com"
        val result = repository.signUp("New User", email, "securepass")
        assertTrue(result is AuthResult.VerificationRequired)
        assertEquals(email, (result as AuthResult.VerificationRequired).email)
    }

    @Test
    fun signUp_withBlankName_returnsFailure() = runTest {
        val result = repository.signUp("  ", "newuser@example.com", "securepass")
        assertTrue(result is AuthResult.Failure)
        assertEquals("Name is required", (result as AuthResult.Failure).message)
    }

    @Test
    fun signUp_withShortPassword_returnsFailure() = runTest {
        val result = repository.signUp("New User", "newuser@example.com", "12345")
        assertTrue(result is AuthResult.Failure)
        assertEquals("Password must be at least 6 characters", (result as AuthResult.Failure).message)
    }

    @Test
    fun verifyOtp_withValidSixDigitCode_returnsAuthenticated() = runTest {
        val email = "verify@example.com"
        val result = repository.verifyOtp(email, "123456")
        assertTrue(result is AuthResult.Authenticated)
        val session = (result as AuthResult.Authenticated).session
        assertEquals(email, session.email)
        assertEquals("verified-user-${email.hashCode()}", session.userId)
        assertEquals("Verify", session.displayName)
        assertEquals(false, session.isDemo)
    }

    @Test
    fun verifyOtp_withInvalidCodeLength_returnsFailure() = runTest {
        val result = repository.verifyOtp("verify@example.com", "12345")
        assertTrue(result is AuthResult.Failure)
        assertEquals("Enter the 6-digit code", (result as AuthResult.Failure).message)
    }

    @Test
    fun resendOtp_returnsVerificationRequired() = runTest {
        val email = "resend@example.com"
        val result = repository.resendOtp(email)
        assertTrue(result is AuthResult.VerificationRequired)
        assertEquals(email, (result as AuthResult.VerificationRequired).email)
    }
}
