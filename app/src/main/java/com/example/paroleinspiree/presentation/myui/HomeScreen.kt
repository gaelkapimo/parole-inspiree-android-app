package com.example.paroleinspiree.presentation.myui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.paroleinspiree.R
import kotlinx.coroutines.delay

data class SliderItem(
    val image : Int,
    val message : String
)

val sliderItems = listOf(
    SliderItem(R.drawable.background, "Priez sans cesse"),
    SliderItem(R.drawable.logo, "Bienvenue sur la communauté parole inspirée"),
    SliderItem(R.drawable.verset, "Le passage à mediter aujourd'hui"),
    SliderItem(R.drawable.affiche, "Theme dimanche : Le pardon selon Mathieu")
)

@Composable
fun HomeScreen(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()) {
        Box{
            AsyncImage(
                model = R.drawable.bible,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier.background(Color.Black.copy(0.6f))
            ){
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.height(200.dp)) {
                        // [START android_compose_pager_indicator]
                        val pagerState = rememberPagerState(pageCount = {
                            sliderItems.size
                        })
                        LaunchedEffect(pagerState) {
                            while (true) {
                                delay(4000) // toutes les 3 secondes
                                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                                pagerState.animateScrollToPage(nextPage)
                            }
                        }
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier
                                .fillMaxSize()

                        ) { page ->
                            // Our page content
                            Box(
                                modifier = Modifier.padding(5.dp)
                            ) {
                                AsyncImage(
                                    model = sliderItems[page].image,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .fillMaxSize()

                                )
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                        .background(
                                            Brush.linearGradient(
                                                listOf(
                                                    Color.Transparent, Color.Black.copy(2f)
                                                )
                                            )
                                        )
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = sliderItems[page].message,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                }
                            }
                        }
                        Row(
                            Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color =
                                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                        .size(12.dp)
                                )
                            }
                        }
                        // [END android_compose_pager_indicator]
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Box{
                        LazyVerticalGrid(
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxWidth(),
                            columns = GridCells.Fixed(2),
                        ) {
                            item {
                                Box(
                                    modifier = Modifier.padding(end = 5.dp)
                                ){
                                    Column {
                                        Text("Verset du jour")
                                        OutlinedCard(
                                            shape = ShapeDefaults.Large,
                                            colors = CardDefaults.cardColors(
                                                contentColor = Color.White,
                                                containerColor = Color(56, 67, 107, 255)
                                            )
                                        ){
                                            Column(
                                                verticalArrangement = Arrangement.spacedBy(2.dp),
                                                modifier = Modifier.padding(5.dp)
                                            ) {
                                                Text(
                                                    text = "Psaume 23:1(LSG)",
                                                    color = Color.Green,
                                                    fontSize = 14.sp
                                                )
                                                Text(text = "Le segneur est mon berger : je ne manquerai de rien.",
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Light)
                                                Text("Meditaion",
                                                    color = Color.Green,
                                                    fontSize = 14.sp
                                                )
                                                Text("Dieu reste fidèle à sa parole.",
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Light
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            item {
                                Box{
                                    Column{
                                        Text("")
                                        OutlinedCard(
                                            shape = ShapeDefaults.Large,
                                            colors = CardDefaults.cardColors(
                                                contentColor = Color.White,
                                                containerColor = Color(56, 67, 107, 255)
                                            ),
                                        ){
                                            Column(
                                                verticalArrangement = Arrangement.spacedBy(2.dp),
                                                modifier = Modifier.padding(5.dp)
                                            ) {
                                                Text(
                                                    text = "Mathieu 11:28(LSG)",
                                                    color = Color.Green,
                                                    fontSize = 14.sp
                                                )
                                                Text(text = "Venez à moi, vous tous qui est fatigués et chargés et je vous" +
                                                        " donnerai du repos.",
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Light)
                                                Text("Meditaion",
                                                    color = Color.Green,
                                                    fontSize = 14.sp
                                                )
                                                Text("Rien est impossible à Dieu.",
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Light
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Text("Evenement",
                        modifier = Modifier.padding(5.dp)
                    )
                    val infiniteTransition = rememberInfiniteTransition(label = "")
                    val offsetX by infiniteTransition.animateFloat(
                        initialValue = -300f,
                        targetValue = -620f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(
                                durationMillis = 16000,
                                easing = LinearEasing
                            )
                        ),
                        label = ""
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(horizontal = 5.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {

                        AsyncImage(
                            model = R.drawable.eventone,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .height(32.dp)
                                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                        ) {

                            // TEXTE 1
                            Text(
                                text = "      Samedi le 04/04 - Reunion des esprits — Tous invités",
                                color = Color.White,
                                fontSize = 15.sp,
                                maxLines = 1,
                                modifier = Modifier
                                    .offset(x = offsetX.dp)
                                    .padding(start = 8.dp)
                            )

                            // TEXTE 2 (clone)
                            Text(
                                text = "      Samedi le 04/04 - Reunion des esprits — Tous invités",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                modifier = Modifier
                                    .offset(x = (offsetX + 400).dp)
                                    .padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}