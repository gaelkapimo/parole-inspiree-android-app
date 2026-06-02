package com.example.paroleinspiree.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.paroleinspiree.route.rails.Route
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    navController: NavController
) {

    val authId = FirebaseAuth.getInstance().currentUser?.uid
    var text by remember { mutableStateOf("") }
    var selectedMessageId by remember { mutableStateOf<String?>(null) }
    val listState = rememberLazyListState()

    // Scroll auto sécurisé
    LaunchedEffect(viewModel.messages.size) {
        if (viewModel.messages.isNotEmpty()) {
            listState.animateScrollToItem(viewModel.messages.lastIndex)
        }
    }

    // Mode édition
    LaunchedEffect(viewModel.editingMessage) {
        text = viewModel.editingMessage?.text ?: ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Box(modifier = Modifier
            .height(50.dp).background(Color(0xFF32722D))
            .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("home"){
                            popUpTo("home"){
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                Text(text = "Messagerie",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
        LazyColumn(
            state = listState,
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(viewModel.messages) { msg ->

                val isMe = msg.senderId == authId
                val isSelected = selectedMessageId == msg.id

                MessageBubble(
                    message = msg,
                    isMe = isMe,
                    isSelected = isSelected,
                    onClick = {
                        selectedMessageId =
                            if (isSelected) null else msg.id
                    },
                    onEdit = {
                        viewModel.startEdit(it)
                        selectedMessageId = null
                    },
                    onDelete = {
                        viewModel.delete(it)
                        selectedMessageId = null
                    }
                )
            }
        }

        Row(
            Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Message…") },
                shape = ShapeDefaults.Large
            )

            Spacer(modifier = Modifier.width(6.dp))

            IconButton(
                onClick = {
                    viewModel.send(text)
                    text = ""
                },
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF32722D))
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}