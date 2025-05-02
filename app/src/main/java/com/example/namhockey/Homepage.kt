import android.widget.Space
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key.Companion.Window
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import com.example.namhockey.R
import com.google.rpc.Help
import kotlinx.coroutines.launch

data class NewsItem(val title: String, val description: String, val imageRes: Int)
data class HighlightItem(val imageRes: Int, val title: String)
data class teamItem( val imageRes: Int, val name: String)

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

    val teamList = listOf(

        teamItem(R.drawable.teama, "My Team" ),
        teamItem(R.drawable.team3, "The School of excellence hockey Club"),
        teamItem(R.drawable.team5, "Windhoek Old Boys"),
        teamItem(R.drawable.team4, "DTS Hockey Club")
    )

    val highlightList = listOf(
        HighlightItem(R.drawable.hockeysample, "Epic Shootout!"),
        HighlightItem(R.drawable.hockeysample, "Insane Save!"),
        HighlightItem(R.drawable.hockeysample, "Buzzer Beater Goal!")
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior() // <- scroll behavior

    val listState = rememberLazyListState()


    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column (
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ){
                    Spacer(Modifier.height(12.dp))

                    Image(
                        painter = painterResource(id = R.drawable.profilepic),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .border(
                                BorderStroke(4.dp, Color.Blue),
                                CircleShape
                            )
                            .padding(4.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Taapopi Ndeshipanda",
                        fontWeight = FontWeight.Bold
                    )

                    NavigationDrawerItem(
                        label = { Text("Account") },
                        icon = { Icon(Icons.Outlined.Person, contentDescription = null) },
                        selected = false,
                        onClick = {}
                    )

                    HorizontalDivider()

                    Text("Get Involved", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Find Club") },
                        selected = false,
                        onClick = {/* handle click */}
                    )
                    NavigationDrawerItem(
                        label = { Text("Register Club") },
                        selected = false,
                        onClick = {/* handle click */}
                    )
                    NavigationDrawerItem(
                        label = { Text("Coach") },
                        selected = false,
                        onClick = {/* handle click */}
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Help & Settings", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = {Text("Settings")},
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        onClick = {/*Handle Click*/}
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Call, contentDescription = null) },
                        onClick = { /* Handle click */ },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    )
    {
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
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }

                            }

                        }) {
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
                        TeamLazy(team = teamList)
                    }


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
}

@Composable
fun TeamCard(team: teamItem){

    Image(
        painter = painterResource(id = team.imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
            .padding(10.dp)
            .border(
                BorderStroke(3.dp, Color.Yellow),
                CircleShape
            )
            .clip(CircleShape)

    )
}


@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF0288D1),
        modifier = Modifier
            .padding(vertical = 12.dp)
    )
}


@Composable
fun NewsCard(news: NewsItem, onClick: () -> Unit) {
    var pressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(targetValue = if (pressed) 0.97f else 1f, label = "scale")

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .clickable(
                onClick = onClick,
                onClickLabel = "Open news"
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        tryAwaitRelease()
                        pressed = false
                    }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = news.imageRes),
                contentDescription = "News Image",
                contentScale = ContentScale.Crop
                ,modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = news.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(6.dp))
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
fun TeamLazy(team: List<teamItem>) {
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        items(team) {team -> TeamCard(team = team)}
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
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .padding(horizontal = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = highlight.imageRes),
                contentDescription = "Highlight Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            Text(
                text = highlight.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(12.dp),
                color = Color(0xFF0288D1)
            )
        }
    }
}

