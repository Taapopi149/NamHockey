import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.namhockey.R

data class Player(val name: String, val position: String, val imageRes: Int)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamPage(navController: NavController) {
    val context = LocalContext.current

    val teamDescription = "Saints Hockey Club commonly known as Saints, is a Namibian Field & Indoor hockey club based in Windhoek, Namibia. It was established on 01 January 2015..."
    val teamWebsite = "https://saintshockeynamibia.wixsite.com/saints"
    val teamLogo = R.drawable.saintsr
    val posts = List(3) { R.drawable.playerstournamentgame }
    val players = listOf(
        Player("John M.", "Forward", R.drawable.propic),
        Player("Liza K.", "Goalkeeper", R.drawable.propic),
        Player("Timo B.", "Defender", R.drawable.propic)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Team Profile", fontWeight = FontWeight.SemiBold) },
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
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = teamLogo),
                        contentDescription = "Team Logo",
                        modifier = Modifier
                            .border(BorderStroke(3.dp, color = Color.Black), CircleShape)
                            .size(120.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,

                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = teamDescription,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(teamWebsite))
                        context.startActivity(intent)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Visit Website", fontWeight = FontWeight.SemiBold)
                }

                OutlinedButton(
                    onClick = {
                        navController.navigate("PlayerReg")
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Register as Player", fontWeight = FontWeight.SemiBold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Team Players", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(players.size) {
                    PlayerCard(player = players[it])
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Team Highlights", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(posts) { post ->
                    Image(
                        painter = painterResource(id = post),
                        contentDescription = "Post Image",
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun PlayerCard(player: Player) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .width(120.dp)
            .padding(vertical = 4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = player.imageRes),
                contentDescription = player.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(player.name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(player.position, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TeamPagePreview() {
    val navController = rememberNavController()

    MaterialTheme {
        TeamPage(navController = navController)
    }
}
