package com.example.paroleinspiree.presentation.myui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.window.Dialog
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.paroleinspiree.R

// Étape 1 : On ajoute le champ info dans la data class
data class Don(
    val image: Int,
    val fournisseur: String,
    val info: String
)

val donItem = listOf(
    Don(R.drawable.money_airtel, "Airtel Money", "Numéro de compte: 123456789"),
    Don(R.drawable.mpesa, "M-Pesa", "Numéro de compte: 987654321"),
    Don(R.drawable.orange, "Orange Money", "Numéro de compte: 1122334455"),
    Don(R.drawable.card_bank, "Virement Bancaire", "IBAN: FR76 1234 5678 9012 3456 7890 123")
)

@Composable
fun DonScreen(navController: NavController){
    var showPopup by remember { mutableStateOf(false) }
    var selectedDon by remember { mutableStateOf<Don?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.background(Color.LightGray)) {
            // --- Ton card Malachie 3:10 ---
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        text = "Malachie 3:10",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(6, 190, 0, 255)
                    )
                    androidx.compose.material3.HorizontalDivider(modifier = Modifier.fillMaxWidth())
                    Text(
                        text = "Apportez à la maison du trésor toutes les dîmes, afin qu’il y ait de la nourriture dans ma maison ; " +
                                "mettez-moi de la sorte à l’épreuve, dit l’Éternel des armées. Et vous verrez " +
                                "si je n’ouvre pas pour vous les écluses des cieux, si je ne répands pas sur vous la bénédiction en abondance.",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                "Méthodes de paiements",
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(10.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(donItem) { item ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(60.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            )
                            .clickable {
                                selectedDon = item
                                showPopup = true
                            },
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = item.image,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(40.dp)
                            )
                            Text(
                                "${item.fournisseur}",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Light
                            )
                            Icon(
                                Icons.Outlined.KeyboardArrowRight,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }

        // --- Popup moderne ---
        if (showPopup && selectedDon != null) {

            Dialog(
                onDismissRequest = { showPopup = false }
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        AsyncImage(
                            model = selectedDon!!.image,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp)
                        )

                        Text(
                            selectedDon!!.fournisseur,
                            fontSize = 18.sp,
                            color = Color(0xFF00BE00),
                            fontWeight = FontWeight.SemiBold
                        )

                        Text(
                            selectedDon!!.info,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )

                        Button(
                            onClick = { showPopup = false }
                        ) {
                            Text("Fermer")
                        }
                    }
                }
            }
        }
    }
}
