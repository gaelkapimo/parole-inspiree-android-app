package com.example.paroleinspiree.data.repository

import android.content.Context
import android.credentials.GetCredentialException
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.paroleinspiree.domain.repository.AuthRepository
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await


class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthRepository {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override suspend fun signInWithGoogle(context: Context): Result<Unit> {
       return try {
           val credentialManager = CredentialManager.create(context)
           val googleIdOption = GetSignInWithGoogleOption.Builder("987766817307-s1fbp7il41ta0aq7ksapdjke5l016p0m.apps.googleusercontent.com")
               .build()

           val request = GetCredentialRequest.Builder()
               .addCredentialOption(googleIdOption)
               .build()

           val result = credentialManager.getCredential(
               context = context,
               request = request
           )

           val credential = result.credential
           if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
               val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
               val idToken = googleCredential.idToken

               val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

               firebaseAuth.signInWithCredential(firebaseCredential).await()
               Result.success(Unit)
           }else{
               Result.failure(Exception("Type de credential non supporté"))
           }
       }catch (e: GetCredentialException){
           Result.failure(Exception("Erreur CredentialManager:${e.message}"))
       }catch (e: Exception){
           Result.failure(e)
       }
    }
}