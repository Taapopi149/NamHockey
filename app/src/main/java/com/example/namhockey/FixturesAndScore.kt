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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Event(
    val day: String,
    val month: String,
    val title: String,
    val location: String,
    val time: String
)


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

            val upcomingEvents = listOf(
                Event("24", "JUN", "National Championship", "Windhoek Stadium", "09:00 AM"),
                Event("30", "JUN", "Junior Tournament", "Sports Complex", "10:30 AM")
            )

            UpcomingEventsSection(events = upcomingEvents)

            Spacer(modifier = Modifier.height(16.dp))



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
fun UpcomingEventsSection(events: List<Event>) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Upcoming Events", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            TextButton(onClick = { /* handle view all */ }) {
                Text("View All")
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(events) { event ->
                Card(
                    modifier = Modifier.width(200.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${event.day} ${event.month}", fontWeight = FontWeight.Bold, color = Color.Blue)
                        Text(event.title, fontWeight = FontWeight.Bold)
                        Text(event.location, fontSize = 12.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(event.time, color = Color.Blue, fontWeight = FontWeight.SemiBold)
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

@Preview(showBackground = true)
@Composable
fun FixturesAndScoresPreview() {
    val navController = rememberNavController()
    FixturesAndScores(navController = navController)
}