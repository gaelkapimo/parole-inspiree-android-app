package com.example.paroleinspiree.presentation.signin

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.paroleinspiree.R
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInCredential
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel){
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    val activity = context as Activity
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        AsyncImage(
            model = R.drawable.peace,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Brush.linearGradient(listOf(
                    Color.Transparent, Color.Black.copy(2f)
                )))
        ){
            if(state is AuthState.Loading || state is AuthState.Success){
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(6.dp)
                        .align(Alignment.TopCenter),
                    color = Color(0xE11A2733)
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(top = 220.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenue sur la plateforme Parole Inspirée",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 35.sp
                )
                Spacer(modifier = Modifier.height(100.dp))

                Text("Pour vous connectez veuillez cliquez sur le boutton en dessous, assurez-vous que vous possedez un compte Google.")

                Spacer(modifier = Modifier.height(60.dp))

                OutlinedButton(onClick = {
                    viewModel.signInWithGoogle(context)
                }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Se connecter avec ")
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }
        }
    }
    when(state){
        is AuthState.Success -> {
            navController.navigate("main"){
                popUpTo("login"){
                    inclusive = true
                }
            }
            Toast.makeText(context, "Connexion reussi avec succès", Toast.LENGTH_SHORT).show()
        }
        is AuthState.Error -> {
            val message = (state as AuthState.Error).message
            LaunchedEffect(message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        else -> {}
    }
}