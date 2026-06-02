package com.example.paroleinspiree.data.repository

import com.example.paroleinspiree.data.model.Message
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class ChatRepository {

    private val db = FirebaseFirestore.getInstance()
    private val messagesRef = db.collection("group_chat")

    fun sendMessage(message: Message) {
        if (message.text.isBlank()) return

        val data = hashMapOf(
            "senderId" to message.senderId,
            "senderName" to message.senderName,
            "text" to message.text,
            "timestamp" to System.currentTimeMillis()
        )

        messagesRef.add(data)
    }

    fun updateMessage(message: Message) {
        if (message.id.isBlank()) return

        messagesRef.document(message.id)
            .update("text", message.text)
    }

    fun deleteMessage(message: Message) {
        if (message.id.isBlank()) return
        messagesRef.document(message.id).delete()
    }

    fun listenMessages(onResult: (List<Message>) -> Unit) {

        messagesRef
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }

                val messages = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(Message::class.java)
                        ?.copy(id = doc.id)
                } ?: emptyList()

                onResult(messages)
            }
    }
}