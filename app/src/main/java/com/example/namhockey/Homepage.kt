import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import com.example.namhockey.NewsViewModel
import com.example.namhockey.R
import kotlinx.coroutines.launch
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.namhockey.Constant
import com.example.namhockey.Snippet
import com.example.namhockey.YouTubeViewModel


data class teamItem( val imageRes: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController, newsViewModel: NewsViewModel,
             youTubeViewModel: YouTubeViewModel
             ) {

    var searchQuery by remember { mutableStateOf("") }

//    val filterdTeams = teamList.filter {
//        it.name.contains(searchQuery, ingoreCase - true)
//    }

    val articleList by newsViewModel.articles.observeAsState(emptyList())


    val videoIds = listOf("N0EmKOquFYk", "Zxmq99kcqe0", "p_ZXFLMCmK4" , "RNxK3byDMuk", "swDpcEH-CnI")
    val viewModel: YouTubeViewModel = viewModel()
    val snippets = viewModel.videoData

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.loadVideos(videoIds, apiKey = Constant.youtube)
        isLoading = false
    }



    val teamList = listOf(

        teamItem(R.drawable.teama, "My Team" ),
        teamItem(R.drawable.team3, "The School of excellence hockey Club"),
        teamItem(R.drawable.team5, "Windhoek Old Boys"),
        teamItem(R.drawable.team4, "DTS Hockey Club")
    )

    LaunchedEffect(Unit) {
        viewModel.loadVideos(videoIds, apiKey = Constant.youtube)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

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
                                BorderStroke(4.dp, Color.Black),
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
                        onClick = { navController.navigate("profile")}
                    )

                    HorizontalDivider()

                    Text("Get Involved", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Find Club") },
                        selected = false,
                        onClick = {navController.navigate("findClub")}
                    )
                    NavigationDrawerItem(
                        label = { Text("Register Club") },
                        selected = false,
                        onClick = {navController.navigate("registerClub")}
                    )
                    NavigationDrawerItem(
                        label = { Text("Coach") },
                        selected = false,
                        onClick = {navController.navigate("coach")}
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
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                    title = {
                        Text(
                            text = "Nam Hockey Union",
                            fontSize = 20.sp,
                            color = Color.Black,
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

                    actions = {
                        IconButton(onClick = { navController.navigate("fixtures") }) {
                            Icon(
                                painter = painterResource(id = R.drawable.bell),
                                contentDescription = "Fixtures",
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )

            },
            content = { innerPadding ->
                LazyColumn(
                    state = listState,
                    contentPadding = innerPadding,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {

                    item {
                        OutlinedTextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            shape = RoundedCornerShape(25.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface
                            ),
                            textStyle = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            singleLine = true
                        )
                    }

              item {
                  SectionTitle("Most Searched Team")
              }

                    item {
                        TeamLazy(team = teamList)
                    }

                    item {
                        SectionTitle(title = "Game Highlights")
                        HorizontalDivider()
                    }

                    item {
                        HighlightCarousel(videoIds = videoIds, snippets = snippets, isLoading = isLoading)
                    }


                    item {
                        HorizontalDivider()
                        SectionTitle(title = "Latest News")
                    }

                    items(articleList) { article ->
                        NewsCard(article = article, navController = navController)
                        HorizontalDivider()
                    }
                }
            }
        )
    }
}

@Composable
fun TeamCard(team: teamItem){

    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }


    Image(
        painter = painterResource(id = team.imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(90.dp)
            .padding(10.dp)
            .border(
                BorderStroke(3.dp,rainbowColorsBrush),
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
        color = Color.Black,
        modifier = Modifier
            .padding(vertical = 12.dp)
    )
}

@Composable
fun NewsCard(article: Article, navController: NavController) {
    var pressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (pressed) 0.97f else 1f, label = "scale")

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .clickable {
                val encodedTitle = Uri.encode(article.title ?: "No Title")
                val encodedDescription = Uri.encode(article.description ?: "No Description")
                val encodedImageUrl = Uri.encode(article.urlToImage ?: "")

                navController.navigate("newsDetail/$encodedTitle/$encodedDescription/$encodedImageUrl")
            }
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    pressed = true
                    tryAwaitRelease()
                    pressed = false
                })
            },
    ) {
        Column {
            article.urlToImage?.let { imageUrl ->
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "News Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = article.title ?: "No Title",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = article.description ?: "No Description",
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
fun HighlightCarousel(
    videoIds: List<String>,
    snippets: Map<String, Snippet>,
    isLoading: Boolean
) {
    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        snippets.isEmpty() -> {
            Text(
                text = "No highlights available at the moment.",
                modifier = Modifier.padding(16.dp),
                color = Color.Gray
            )
        }

        else -> {
            LazyRow {
                items(videoIds) { id ->
                    snippets[id]?.let {
                        HighlightCard(videoId = id, snippet = it)
                    }
                }
            }
        }
    }
}


@Composable
fun HighlightCard(videoId: String, snippet: Snippet) {
    val context = LocalContext.current
    val videoUrl = "https://www.youtube.com/watch?v=$videoId"

    Card(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(videoUrl)
                setPackage("com.google.android.youtube")

            }
                context.startActivity(intent)

            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium,
        //shape = RoundedCornerShape(16.dp),

    ) {
        Column {
            AsyncImage(
                model = snippet.thumbnails.medium.url,
                contentDescription = "YouTube Thumbnail",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = snippet.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}


