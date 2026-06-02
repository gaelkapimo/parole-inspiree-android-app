package com.example.paroleinspiree.presentation.signin

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.paroleinspiree.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (private val repository: AuthRepository): ViewModel() {
    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)

    val state: StateFlow<AuthState> = _state

    fun signInWithGoogle(context: Context){
        viewModelScope.launch {
            _state.value = AuthState.Loading

            val result = repository.signInWithGoogle(context)

            _state.value = result.fold(
                onSuccess = {
                    AuthState.Success
                },
                onFailure = { e ->
                    AuthState.Error(
                        message = e.message?:"ERREUR: Inconnue",
                        cause = e
                    )
                }
            )
        }
    }

    fun resetState(){
    _state.value = AuthState.Idle
    }
}