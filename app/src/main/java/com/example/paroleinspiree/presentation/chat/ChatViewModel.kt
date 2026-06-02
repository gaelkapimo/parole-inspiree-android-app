package com.example.paroleinspiree.presentation.chat

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.paroleinspiree.data.model.Message
import com.example.paroleinspiree.data.repository.ChatRepository
import com.google.firebase.auth.FirebaseAuth

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository()
    private val auth = FirebaseAuth.getInstance()

    var messages by mutableStateOf<List<Message>>(emptyList())
        private set

    var editingMessage by mutableStateOf<Message?>(null)
        private set

    init {
        if (auth.currentUser != null) {
            listenMessages()
        }
    }

    private fun listenMessages() {
        repository.listenMessages {
            messages = it
        }
    }

    fun send(text: String) {
        val user = auth.currentUser ?: return
        if (text.isBlank()) return

        if (editingMessage != null) {
            repository.updateMessage(
                editingMessage!!.copy(text = text)
            )
            editingMessage = null
        } else {
            repository.sendMessage(
                Message(
                    senderId = user.uid,
                    senderName = user.displayName ?: "User",
                    text = text
                )
            )
        }
    }

    fun startEdit(message: Message) {
        editingMessage = message
    }

    fun cancelEdit() {
        editingMessage = null
    }

    fun delete(message: Message) {
        repository.deleteMessage(message)
    }
}