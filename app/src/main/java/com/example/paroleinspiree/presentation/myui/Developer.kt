package com.example.paroleinspiree.presentation.myui


/*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Whatsapp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.paroleinspiree.R

@Composable
fun developerUi(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ){
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ){
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = R.drawable.card_biographie,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .fillMaxSize()
                            .clip(CircleShape)
                        )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column() {
                        Text(text = "Gael Kap",
                            fontSize = 13.sp,
                            color = Color.Black
                        )
                        Text("Software engineer",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Row() {
                            IconButton(
                                onClick = {}
                            ) {
                                Icon(
                                    Icons.Outlined.Whatsapp,
                                    contentDescription = null,
                                    modifier = Modifier.size(22.dp),
                                    tint = Color.Green
                                )
                            }
                            IconButton(onClick = {}) {
                                Icon(
                                    Icons.Outlined.Call,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp),
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .height(150.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ){
                Text(
                    text = "Transformez vos idées en solution numériques !" +
                            " Nous créons des applications Android, iOS et" +
                            "des sites web pour une digitalisation moderne et" +
                            " d'intelligence artificielle.",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}
*/