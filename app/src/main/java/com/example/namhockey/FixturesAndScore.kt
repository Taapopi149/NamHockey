package com.example.namhockey

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FixturesAndScores(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fixtures & Tables") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Live Score",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Namibia 3 - 2 South Africa", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Text("Final", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }


            HorizontalDivider()

            Text("Upcoming Fixtures", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            // Add your fixture list here

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Namibia vs Zambia", fontWeight = FontWeight.SemiBold)
                    Text("Date: 12 May 2025 | Time: 16:00", fontSize = 14.sp, color = Color.Gray)
                }
            }


            HorizontalDivider()


            Text("Group Tables", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            // Add your group table UI here

            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Team", fontWeight = FontWeight.Bold)
                    Text("P", fontWeight = FontWeight.Bold)
                    Text("W", fontWeight = FontWeight.Bold)
                    Text("L", fontWeight = FontWeight.Bold)
                    Text("Pts", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                listOf(
                    listOf("Namibia", "3", "2", "1", "6"),
                    listOf("South Africa", "3", "2", "1", "6"),
                    listOf("Zambia", "3", "1", "2", "3")
                ).forEach { row ->
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        row.forEach { cell ->
                            Text(cell)
                        }
                    }
                }
            }


        }
    }
}
