package com.example.paroleinspiree.presentation.myui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.paroleinspiree.R

// -------------------- DATA --------------------
data class PrayerItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val color: Color,
    val verse: String,
    val encouragement: String
)

// -------------------- MAIN SCREEN --------------------
@Composable
fun PrayerScreen(navController: NavController) {
    var selectedPrayer by remember { mutableStateOf<PrayerItem?>(null) }

    val prayers = listOf(
        PrayerItem(
            "Je désire faire la prière",
            "Entrer dans la prière personnelle",
            Icons.Default.Favorite,
            Color.Red,
            verse = "Jean 14:13 – Tout ce que vous demanderez en mon nom",
            encouragement = "Ouvre ton cœur et parle à Dieu aujourd'hui"
        ),
        PrayerItem(
            "Je renouvelle mon engagement",
            "Recommencer sa marche avec Dieu",
            Icons.Default.Refresh,
            Color.Blue,
            verse = "Romains 12:1 – Présentez vos corps comme un sacrifice vivant",
            encouragement = "Dieu renouvelle tes forces et ton engagement"
        ),
        PrayerItem(
            "Je désire intégrer la communauté Parole Inspirée",
            "Rejoindre l’église et la communauté",
            Icons.Default.People,
            Color.Green,
            verse = "Hébreux 10:24-25 – Encourageons-nous les uns les autres",
            encouragement = "Rejoins-nous pour grandir ensemble dans la foi"
        ),
        PrayerItem(
            "Je désire prendre un rendez-vous",
            "Contacter le pasteur pour guidance",
            Icons.Default.Event,
            Color(0xFFFF9800),
            verse = "Proverbes 3:6 – Reconnais-le dans toutes tes voies",
            encouragement = "Fixe un moment pour recevoir un conseil spirituel"
        ),
        PrayerItem(
            "J’ai besoin d’un appel",
            "Être contacté par l’église",
            Icons.Default.Phone,
            Color(0xFF9C27B0),
            verse = "Psaume 34:18 – L’Éternel est proche de ceux qui ont le cœur brisé",
            encouragement = "Un membre de l’église vous contactera bientôt"
        )
    )

    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        if (selectedPrayer != null) {
            PrayerDetailScreen(item = selectedPrayer!!) {
                selectedPrayer = null
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {

                // 🔥 HEADER IMAGE
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .shadow(10.dp)
                ) {

                    AsyncImage(
                        model = R.drawable.background, // ton image actuelle
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Overlay sombre
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.45f))
                    )

                    // Texte
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Espace de prière",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = "Un temps pour parler à Dieu",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 🔲 GRID AVEC ICÔNES
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    items(prayers) { item ->
                        PrayerCard(item) {
                            selectedPrayer = item
                        }
                    }
                }
            }
        }
    }
}

// -------------------- CARD --------------------
@Composable
fun PrayerCard(item: PrayerItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color(89, 89, 89, 255)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                tint = item.color,
                modifier = Modifier.size(28.dp)
            )

            Column {
                Text(
                    text = item.title,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium,
                    fontSize = 16.sp
                )
                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// -------------------- DETAIL + FORMULAIRE --------------------
@Composable
fun PrayerDetailScreen(item: PrayerItem, onBack: () -> Unit) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var whatsapp by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = item.title, fontSize = 22.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold)
        Text(text = item.verse, fontSize = 16.sp, color = Color.DarkGray)
        Text(text = item.encouragement, fontSize = 14.sp, color = Color.Black.copy(alpha = 0.85f))

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nom") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = whatsapp, onValueChange = { whatsapp = it }, label = { Text("WhatsApp") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = message, onValueChange = { message = it }, label = { Text("Message au pasteur") }, modifier = Modifier.fillMaxWidth().height(120.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && email.isNotBlank() && message.isNotBlank()) {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:pasteur@eglise.com") // <-- email placeholder
                        putExtra(Intent.EXTRA_SUBJECT, "Message depuis l'app de l'église")
                        putExtra(Intent.EXTRA_TEXT,
                            "Nom: $name\nEmail: $email\nWhatsApp: $whatsapp\n\nMessage:\n$message\n\nCe message a été envoyé depuis l’application de l’église Parole Inspirée."
                        )
                    }
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "Impossible d'envoyer le mail", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("Envoyer au pasteur")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp)) {
            Text("Retour")
        }
    }
}