package com.example.paroleinspiree.presentation.signin

sealed class AuthState {
    object Idle: AuthState()
    object Loading: AuthState()
    object Success: AuthState()
    data class Error(
        val message: String,
        val cause: Throwable
    ): AuthState()
}