package com.example.paroleinspiree.presentation.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.BabyChangingStation
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.paroleinspiree.R
import com.example.paroleinspiree.live.LiveScreen
import com.example.paroleinspiree.live.LiveViewModel
import com.example.paroleinspiree.live.LiveViewModelFactory
import com.example.paroleinspiree.presentation.chat.ChatScreen
import com.example.paroleinspiree.presentation.chat.ChatViewModel
import com.example.paroleinspiree.presentation.myui.Departement
import com.example.paroleinspiree.presentation.myui.DonScreen
import com.example.paroleinspiree.presentation.myui.HomeScreen
import com.example.paroleinspiree.presentation.myui.Information
import com.example.paroleinspiree.presentation.myui.PrayerScreen
import com.example.paroleinspiree.route.rails.Route
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.inappmessaging.FirebaseInAppMessaging
import compose.icons.FeatherIcons
import compose.icons.TablerIcons
import compose.icons.feathericons.LogOut
import compose.icons.feathericons.Tv
import compose.icons.tablericons.Home
import compose.icons.tablericons.Man
import compose.icons.tablericons.Messages
import compose.icons.tablericons.Pray
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavUi(
    authViewModel: AuthViewModel,
    rootNavController: NavController
) {
    val systemUiController = rememberSystemUiController()
    val user = FirebaseAuth.getInstance().currentUser

    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val primaryColor = Color(73, 71, 134)
    val drawerHeaderColor = Color(0xFF32722D)
    val drawerBackgroundColor = Color(0xE11A2733)

    // ✅ LISTE UNIQUE ET CORRECTE
    val items = listOf(
        Triple("home", TablerIcons.Home, "Accueil"),
        Triple("pray", TablerIcons.Pray, "Prières"),
        Triple("don", Icons.Outlined.BabyChangingStation, "Dons"),
        Triple("chat", TablerIcons.Messages, "Message"),
        Triple("live", FeatherIcons.Tv, "Live")
    )
    SideEffect {
        systemUiController.setStatusBarColor(Color.Black, darkIcons = false)
    }

    var selectedRoute by rememberSaveable { mutableStateOf("home") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = drawerBackgroundColor,
                modifier = Modifier.width(300.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(drawerHeaderColor)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Card(
                                modifier = Modifier.size(75.dp),
                                shape = CircleShape
                            ) {
                                AsyncImage(
                                    model = user?.photoUrl,
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = user?.displayName ?: "Utilisateur",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    "Membre",
                                    fontSize = 12.sp,
                                    color = Color.LightGray
                                )
                            }
                        }
                    }

                    Divider()

                    Text(
                        "Notre communauté",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )

                    NavigationDrawerItem(
                        icon = { Icon(TablerIcons.Man, null) },
                        label = { Text("Département") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("departement"){
                                popUpTo(Route.MAIN){
                                    inclusive = false
                                }
                            }
                        }
                    )
                    NavigationDrawerItem(
                        icon = { Icon(Icons.Filled.Info, null) },
                        label = { Text("Informations") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("info"){
                                popUpTo(Route.MAIN){
                                    inclusive = false
                                }
                            }
                        }
                    )

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Outlined.Settings, null) },
                        label = { Text("Paramètre") },
                        selected = false,
                        onClick = {}
                    )

                    NavigationDrawerItem(
                        icon = { Icon(Icons.Outlined.Logout, null) },
                        label = { Text("Deconnexion") },
                        selected = false,
                        onClick = {
                                FirebaseAuth.getInstance().signOut()
                                authViewModel.resetState()
                                rootNavController.navigate(Route.LOGIN) {
                                    popUpTo(Route.MAIN) { inclusive = true }
                                }
                            },

                        )
                    Text(
                        text = "By Gael KAPIMO KANIKI",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Thin,
                        modifier = Modifier.padding(vertical = 100.dp, horizontal = 20.dp),
                        color = Color.Gray
                    )
                    /*
                    NavigationDrawerItem(
                        icon = { Icon(FeatherIcons.Code, null) },
                        label = { Text("Développeur") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            navController.navigate("dev"){
                                popUpTo(Route.MAIN){
                                    inclusive = false
                                }
                            }
                        }
                    )
                     */
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(primaryColor),
                    title = {
                        Column() {
                            Text(
                                "Communauté",
                                fontSize = 12.sp
                            )
                            Text(
                                "Parole inspirée",
                                fontSize = 12.sp
                            )
                        }
                    },
                    navigationIcon = {
                        AsyncImage(
                            model = R.drawable.logo,
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                        )
                    },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open()
                                else drawerState.close()
                            }
                        }) {
                            Icon(Icons.Outlined.Menu, null)
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = primaryColor,
                    modifier = Modifier
                        .systemBarsPadding()
                        .height(55.dp)
                ) {
                    items.forEach { (route, icon, label) ->
                        NavigationBarItem(
                            selected = selectedRoute == route,
                            onClick = {
                                selectedRoute = route
                                navController.navigate(route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo("home") { inclusive = false }
                                }
                            },
                            icon = {
                                Icon(icon, null, Modifier.size(24.dp))
                            },
                            label = {
                                Text(label, fontWeight = FontWeight.Light)
                            }
                        )
                    }
                }
            }
        ) { padding ->
            Box(Modifier.padding(padding)) {
                NavHost(navController, startDestination = "home") {
                    composable("home") { HomeScreen(navController) }
                    composable("info") { Information(navController) }
                    composable("departement") { Departement(navController) }
                    composable("don") { DonScreen(navController) }
                    composable("pray") { PrayerScreen(navController) }
                    composable("chat") {
                        val vm: ChatViewModel = viewModel()
                        ChatScreen(vm, navController)
                    }
                    composable("live") {
                        // Instancie le ViewModel MVVM
                        val liveViewModel: LiveViewModel = viewModel(factory = LiveViewModelFactory())

                        // Firebase In-App Messaging listener
                        FirebaseInAppMessaging.getInstance()
                            .addClickListener { _, action ->
                                val data = action.actionUrl ?: return@addClickListener
                                val uri = android.net.Uri.parse(data)

                                // Corrigé : on récupère le vrai paramètre videoId
                                val videoId = uri.getQueryParameter("videoId") ?: ""
                                val title = uri.getQueryParameter("title") ?: "Live en cours"

                                // On met à jour le live dans le ViewModel
                                liveViewModel.setLive(videoId, title)
                            }

                        // Affiche le LiveScreen avec le ViewModel
                        LiveScreen(viewModel = liveViewModel)
                    }
                }
            }
        }
    }
}
