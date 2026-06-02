package com.example.paroleinspiree.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.paroleinspiree.data.model.Message
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageBubble(

    message: Message,
    isMe: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    onEdit: (Message) -> Unit,
    onDelete: (Message) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .background(
                    color = if (isMe) Color(0xFF79966E) else Color(0xFFF0F0F0), // bleu pro pour moi, gris clair pour les autres
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomStart = if (isMe) 16.dp else 0.dp,
                        bottomEnd = if (isMe) 0.dp else 16.dp
                    )
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .clickable { onClick() }
        ) {
            // Nom de l'expéditeur pour les messages reçus
            if (!isMe) {
                Text(
                    message.senderName,
                    fontSize = 12.sp,
                    color = Color(0xFF075E54), // vert sombre pro
                    modifier = Modifier.padding(bottom = 2.dp)
                )
            }

            // Texte du message
            Text(
                text = message.text,
                fontSize = 14.sp,
                color = if (isMe) Color.White else Color.Black
            )

            // Date et heure
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(message.timestamp)),
                    fontSize = 10.sp,
                    color = if (isMe) Color.White.copy(alpha = 0.7f) else Color.Gray
                )
                Text(
                    SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(message.timestamp)),
                    fontSize = 10.sp,
                    color = if (isMe) Color.White.copy(alpha = 0.7f) else Color.Gray
                )
            }

            // Boutons Modifier / Supprimer visibles seulement si message sélectionné
            if (isMe && isSelected) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp)
                ) {
                    TextButton(onClick = { onEdit(message) }) {
                        Text("Modifier", fontSize = 12.sp, color = Color(0xFF128C7E))
                    }
                    TextButton(onClick = { onDelete(message) }) {
                        Text("Supprimer", fontSize = 12.sp, color = Color.Red)
                    }
                }
            }
        }
    }
}
