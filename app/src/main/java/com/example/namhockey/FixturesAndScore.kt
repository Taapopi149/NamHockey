package com.example.namhockey

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

            Text("Live Score", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            Spacer(Modifier.height(8.dp))

            LiveMatchCard("Windhoek Old Boys", 3, "DTS Hockey Club", 4, "78'", R.drawable.team5, R.drawable.team4)
            LiveMatchCard("Saints Hockey Club", 2, "Namibia Masters", 2, "45'", R.drawable.saintsr, R.drawable.namibiahockey)
            LiveMatchCard("Coastal Raiders", 1, "Sparta Hockey", 0, "23'", R.drawable.coastalraidersr, R.drawable.sparta)

            Spacer(Modifier.height(16.dp))
            HorizontalDivider()

            Spacer(Modifier.height(10.dp))
            Text("Upcoming Fixtures", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            val upcomingFixtures = listOf(
                "Namibia vs Zambia" to "12 May 2025 | 16:00",
                "South Africa vs Kenya" to "14 May 2025 | 18:00"
            )

            Spacer(Modifier.height(8.dp))
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

            val headers = listOf("Team", "M", "W", "D", "L", "G", "Pts")
            val groupData = listOf(
                listOf("My Team", "3", "3", "0", "0", "9:5", "9"),
                listOf("School", "3", "1", "1", "1", "6:7", "4"),
                listOf("Windhoek Old Boys", "2", "0", "1", "1", "3:4", "1"),
                listOf("DTS Hockey Club", "2", "0", "0", "2", "5:7", "0")
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1B2A)),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
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
fun TeamIconOnly(iconRes: Int, contentDescription: String) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = contentDescription,
        modifier = Modifier.size(32.dp)
    )
}

@Composable
fun LiveMatchCard(
    teamA: String,
    scoreA: Int,
    teamB: String,
    scoreB: Int,
    minute: String,
    iconARes: Int,
    iconBRes: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("LIVE $minute", color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TeamIconOnly(iconARes, teamA)
                Text("$scoreA - $scoreB", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                TeamIconOnly(iconBRes, teamB)
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

@Preview(showBackground = true)
@Composable
fun FixturesAndScoresPreview() {
    val navController = rememberNavController()
    FixturesAndScores(navController = navController)
}
