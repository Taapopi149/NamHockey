package com.example.namhockey

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Login(navController: NavController){

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

        OutlinedTextField(value = "",
            onValueChange = {},
            label = {Text(text="Email address")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(value = "",
            onValueChange = {},
            label = {Text(text="Email address")},
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Blue
            )

            )

        Spacer(modifier = Modifier.height(33.dp))

        Button(
            //NOT DONE COMEBACK
            onClick = {navController.navigate("home")},
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor =  Color(0xFF81D4FA),
                contentColor = Color.Black
            )
        ) {
            Text(text = "Login")
        }

        TextButton(onClick = {

        }) {
            Text(text = "Forgot Password?")
        }

    }

}