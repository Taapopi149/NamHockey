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

            LiveMatchCard("TeamA", 3 ,"TeamB", 4 , "78", "")
            LiveMatchCard("TeamC", 3 ,"TeamD", 4 , "23", "")
            LiveMatchCard("TeamF", 3 ,"TeamD", 4 , "34", "")

            Spacer(Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))
            Text("Upcoming Fixtures", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            // Add your fixture list here

            val upcomingFixtures = listOf(
                "Namibia vs Zambia" to "12 May 2025 | 16:00",
                "South Africa vs Kenya" to "14 May 2025 | 18:00"
            )

            upcomingFixtures.forEach { (match, details) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(match, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(details, fontSize = 14.sp, color = Color.Gray)
                    }
                }
            }


            Spacer(Modifier.height(10.dp))
            HorizontalDivider()

            Spacer(Modifier.height(10.dp))
            Text("Group Tables", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))

            val headers = listOf("Team", "M", "W", "D", "L", "G", "Pts")
            val groupData = listOf(
                listOf("Bayern Munich", "3", "3", "0", "0", "9:5", "9"),
                listOf("Galatasaray", "3", "1", "1", "1", "6:7", "4"),
                listOf("FC Copenhagen", "2", "0", "1", "1", "3:4", "1"),
                listOf("Manchester United", "2", "0", "0", "2", "5:7", "0")
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1B2A)), // dark navy
                elevation = CardDefaults.cardElevation(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Header Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        headers.forEach {
                            Text(
                                text = it,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color.White,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    // Data Rows
                    groupData.forEachIndexed { index, row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            row.forEach {
                                Text(
                                    text = it,
                                    fontSize = 12.sp,
                                    color = Color.LightGray,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        if (index < groupData.lastIndex) {
                            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)
                        }
                    }
                }
            }


        }
    }
}


@Composable
fun LiveMatchCard(teamA: String, scoreA: Int, teamB: String, scoreB: Int, minute: String, scorer: String? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("LIVE $minute", color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                scorer?.let {
                    Text(it, fontSize = 12.sp, color = Color.DarkGray)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(teamA, fontWeight = FontWeight.SemiBold)
                }
                Text("$scoreA - $scoreB", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Column {
                    Text(teamB, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
