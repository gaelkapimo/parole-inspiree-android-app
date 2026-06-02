package com.example.paroleinspiree.presentation.myui

import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.paroleinspiree.R
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowBack

@Composable
fun Information(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column{
            Box(modifier = Modifier
                .height(50.dp).background(Color(0xFF32722D))
                .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
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
                    }
                    Text(text = "Communauté - Infomations",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    ){
                        AsyncImage(
                            model = R.drawable.slide2,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(0.4f))
                        ){
                            Text(
                                "Decouvrez plus d'information sur notre communauté et " +
                                        "leurs responsables ainsi leurs roles au sein de la communauté",
                                modifier = Modifier.padding(top = 40.dp)
                                    .padding(horizontal = 15.dp),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .padding(horizontal = 10.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp // l’ombre est gérée par shadow()
                        )
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text("Notre Mission",
                                color = Color(6, 190, 0, 255),
                                fontWeight = FontWeight.Medium
                                )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Text(
                                "Notre communauté est un espace vivant de foi, d’amour et d’espérance, où chaque cœur est invité à se rapprocher de Dieu par la prière, la Parole et la communion fraternelle. Ici, personne n’est seul : nous marchons ensemble, unis dans le Christ, portés par la même foi et la même confiance en l’action de l’Esprit Saint.\n" +
                                        "\n" +
                                        "Chaque jour, nous prenons le temps de nous arrêter, de nous recueillir et de confier nos vies au Seigneur. À travers la prière quotidienne, les versets bibliques, les intentions partagées et les temps de méditation, nous nourrissons notre relation personnelle avec Dieu et fortifions notre foi, même au milieu des épreuves.\n" +
                                        "\n" +
                                        "Notre mission est simple mais essentielle :\n" +
                                        "prier ensemble, grandir ensemble et aimer comme le Christ nous aime."
                            )
                        }
                    }
                }
            }
        }
    }

@Composable
fun Departement(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Column{
            Box(modifier = Modifier
                .height(50.dp).background(Color(0xFF32722D))
                .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate("home"){
                                popUpTo("home"){
                                    inclusive = true
                                    saveState = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            TablerIcons.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Text(text = "Nos departements",
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                    ){
                        AsyncImage(
                            model = R.drawable.rise,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(0.4f))
                        ){
                            Text(
                                "Decouvrez plus d'information sur notre communauté et " +
                                        "leurs responsables ainsi leurs roles au sein de la communauté",
                                modifier = Modifier.padding(top = 40.dp)
                                    .padding(horizontal = 15.dp),
                                fontWeight = FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp // l’ombre est gérée par shadow()
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text("Les hommes des prières",
                                color = Color(6, 190, 0, 255),
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.size(70.dp),
                                        shape = CircleShape
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.priere,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column() {
                                        Text(
                                            text = "Dirigé par: Frinel Maud",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                        Text(text = "Lieu: Depuis Congo Brazzaville",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Text(
                                    "Mission: Former des hommes de prière, unis dans la foi, engagés" +
                                            " à intercéder et à vivre l’Évangile au quotidien.",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp // l’ombre est gérée par shadow()
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text("Génération Joel CB",
                                color = Color(6, 190, 0, 255),
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.size(70.dp),
                                        shape = CircleShape
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.gen_joel,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column() {
                                        Text(
                                            text = "Dirigé par: Arthur",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                        Text(text = "Lieu: Depuis Congo Brazzaville",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Text(
                                    "Mission: Former des hommes de prière, unis dans la foi, engagés" +
                                            " à intercéder et à vivre l’Évangile au quotidien.",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }

                        }
                    }

            }
                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp // l’ombre est gérée par shadow()
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text("Parole inspirée",
                                color = Color(6, 190, 0, 255),
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.size(70.dp),
                                        shape = CircleShape
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.parole_inspi,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column() {
                                        Text(
                                            text = "Dirigé par:  Francisco Ilunga",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                        Text(text = "Lieu: Depuis la RD Congo",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Text(
                                    "Mission: Former des hommes de prière, unis dans la foi, engagés" +
                                            " à intercéder et à vivre l’Évangile au quotidien.",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp // l’ombre est gérée par shadow()
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text("DFR S.O.H",
                                color = Color(6, 190, 0, 255),
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.size(70.dp),
                                        shape = CircleShape
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.soh,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column() {
                                        Text(
                                            text = "Dirigé par: MErveil Kabila",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                        Text(text = "Lieu: Depuis la RD Congo ",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Text(
                                    "Mission: Pour les oeuvres caritatives soutien aux orphelins.",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
                item {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                            .shadow(
                                elevation = 20.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = false
                            ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp // l’ombre est gérée par shadow()
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally ,
                            modifier = Modifier.padding(5.dp)
                        ) {
                            Text("DFR DEV",
                                color = Color(6, 190, 0, 255),
                                fontWeight = FontWeight.Medium
                            )
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                            )
                            Column(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.size(70.dp),
                                        shape = CircleShape
                                    ) {
                                        AsyncImage(
                                            model = R.drawable.dev,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Column() {
                                        Text(
                                            text = "Dirigé par: Patient Kiwala",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                        Text(text = "Lieu: Depuis la RD Congo",
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Normal
                                        )
                                    }
                                }
                                Text(
                                    "Mission: Pour développement personnel et le partage des differents projets.",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal
                                )
                            }
                        }
                    }
                }
        }
    }
}
}