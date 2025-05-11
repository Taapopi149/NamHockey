package com.example.namhockey

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.namhockey.ui.theme.NamHockeyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Coach(navController: NavController) {
val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Coach") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    )
    {
        padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            CoachCard(
                imageRes = R.drawable.becomeacoach,
                title = "Become a Manager",
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://namibiahockey.org/coach/"))
                    context.startActivity(intent)
                }
            )

            CoachCard(
                imageRes = R.drawable.teammanagement,
                title = "Team Management",
                onClick = { navController.navigate("TeamManagement") }
            )
        }
    }
}

@Composable
fun CoachCard(
    imageRes: Int,
    title: String,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.3f) // Adjust height/width ratio
    ) {
        Box {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.35f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoachPreview() {
    NamHockeyTheme {
        val navController = rememberNavController()
        Coach(navController = navController)
    }
}
