import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.scale
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.namhockey.R

data class NewsItem(val title: String, val description: String, val imageRes: Int)
data class HighlightItem(val imageRes: Int, val title: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage() {
    val newsList = listOf(
        NewsItem(
            title = "Team A wins 4-2!",
            description = "A thrilling match where Team A defeated Team B with an outstanding performance.",
            imageRes = R.drawable.hockeysample
        ),
        NewsItem(
            title = "Player X out with injury!",
            description = "Star Player X suffered a minor injury and is expected to miss 2 weeks.",
            imageRes = R.drawable.hockeysample
        ),
        NewsItem(
            title = "Team C signs new star!",
            description = "Team C has signed a top forward to boost their attack this season.",
            imageRes = R.drawable.hockeysample
        )
    )

    val highlightList = listOf(
        HighlightItem(R.drawable.hockeysample, "Epic Shootout!"),
        HighlightItem(R.drawable.hockeysample, "Insane Save!"),
        HighlightItem(R.drawable.hockeysample, "Buzzer Beater Goal!")
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() // <- scroll behavior

    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nam Hockey Union",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { println("Profile clicked") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Profile",
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black
                ),
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                state = listState,
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection) // <- connect scroll behavior
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                item {
                    SectionTitle(title = "Latest News")
                }
                items(newsList) { news ->
                    NewsCard(news = news, onClick = {
                        println("Clicked on ${news.title}")
                    })
                }

                item {
                    SectionTitle(title = "Game Highlights")
                }

                item {
                    HighlightCarousel(highlights = highlightList)
                }
            }
        }
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF81D4FA),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun NewsCard(news: NewsItem, onClick: () -> Unit) {
    var scale by remember { mutableStateOf(1f) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .scale(scale)
            .clickable {
                scale = 0.95f
                onClick()
                scale = 1f
            }
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = news.imageRes),
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = news.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = news.description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun HighlightCarousel(highlights: List<HighlightItem>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        items(highlights) { highlight ->
            HighlightCard(highlight)
        }
    }
}

@Composable
fun HighlightCard(highlight: HighlightItem) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .size(width = 200.dp, height = 150.dp)
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE1F5FE)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = highlight.imageRes),
                contentDescription = "Highlight Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            Text(
                text = highlight.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
