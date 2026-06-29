package com.tnyx.features.auth.domain.repository

import com.tnyx.features.auth.domain.model.AuthResult

class TestAuthRepository : AuthRepository {
    var signInResult: AuthResult = AuthResult.Failure("Not initialized")
    var signInWithDemoResult: AuthResult = AuthResult.Failure("Not initialized")
    var signUpResult: AuthResult = AuthResult.Failure("Not initialized")
    var verifyOtpResult: AuthResult = AuthResult.Failure("Not initialized")
    var resendOtpResult: AuthResult = AuthResult.Failure("Not initialized")

    override suspend fun signIn(email: String, password: String): AuthResult = signInResult
    override suspend fun signInWithDemoAccount(): AuthResult = signInWithDemoResult
    override suspend fun signUp(name: String, email: String, password: String): AuthResult = signUpResult
    override suspend fun verifyOtp(email: String, code: String): AuthResult = verifyOtpResult
    override suspend fun resendOtp(email: String): AuthResult = resendOtpResult
}
