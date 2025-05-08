package com.example.namhockey

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter


@Composable
fun Login(navController: NavController, viewModel: LoginViewModel){

    val uiState by viewModel.uiState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf(" ") }

    val context = LocalContext.current

    if (uiState.success) {
        LaunchedEffect(Unit) {
            navController.navigate("home") {
                popUpTo("Login") {inclusive = true}
            }
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.hockeylogin), contentDescription = "Hockey Logo",
            modifier = Modifier.size(200.dp)
            )

        Text(text = "Welcome Back", fontSize = 28.sp, fontWeight = FontWeight.Bold )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Login to your account")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = email,
            onValueChange = {email = it},
            label = {Text(text="Email address")},
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = password,
            onValueChange = {password = it},
            label = {Text(text="Password")},
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )

            )

        Spacer(modifier = Modifier.height(33.dp))

        Button(

            onClick = { viewModel.login(email, password)},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0xFF81D4FA),
                contentColor = Color.Black
            ),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Text(text = "Login")
        }


        uiState.error?.let {
            errorMessage -> LaunchedEffect(errorMessage) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
        }

        TextButton(onClick = {

        }) {
            Text(text = "Forgot Password?", color = Color.Black)
        }

    }

}