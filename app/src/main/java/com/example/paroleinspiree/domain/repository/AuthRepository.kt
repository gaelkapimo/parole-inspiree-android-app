package com.example.paroleinspiree.domain.repository


import android.content.Context

interface AuthRepository {
        suspend fun signInWithGoogle(context: Context): Result<Unit>
}