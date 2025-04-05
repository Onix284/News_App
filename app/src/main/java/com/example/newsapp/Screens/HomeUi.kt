package com.example.newsapp.Screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.newsapp.MyViewModel.MyViewModel
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


fun encodeString(encodedStr: String): String{
    return URLEncoder.encode(encodedStr, StandardCharsets.UTF_8.toString())
}

@Composable
fun HomeUI(viewModel: MyViewModel, navController: NavController) {

    val response = viewModel.response.value?.articles ?: emptyList()
    //val scrollState = rememberSaveable {androidx.compose.foundation.lazy.rememberLazyListState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.LightGray), contentAlignment = Alignment.BottomCenter)
        {
            Text("News App", modifier = Modifier.padding(vertical = 30.dp),
                fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif,
                fontSize = 20.sp)
        }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 0.dp),
                //state = scrollState
            ) {
                items(response){
                    NewsPrefab(
                        modifier = Modifier,
                        title = it.title!!,
                        imageLink = it.urlToImage,
                        description = it.description,
                        author = it.author,
                        source = it.source?.name,
                        navController = navController
                    )
                }
            }
        }
}


@Composable
fun NewsPrefab(modifier: Modifier,
               title: String,
               imageLink: String?,
               description: String?,
               author: String?,
               source: String?,
               navController: NavController){

    Card (
        onClick = {
            val encodedTitle = encodeString(title)
            val encodedImageLink = encodeString(imageLink ?: " ")
            val encodedDescription = encodeString(description ?: " ")
            val encodedAuthor = encodeString(author ?: " ")
            val encodedSource = encodeString(source ?: " ")

            navController.navigate("details_screen/$encodedTitle/$encodedImageLink/$encodedDescription/$encodedAuthor/$encodedSource")},

        modifier = Modifier
            .padding(10.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {

        Column {
            AsyncImage(
                model = imageLink,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
            )

            Column(modifier.padding(horizontal = 10.dp)) {
                Text(text = title, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)
            }
        }
    }
}


private fun decodeStr(encodedStr:  String?): String? {
    try {
        return encodedStr?.let { URLDecoder.decode(encodedStr, StandardCharsets.UTF_8.toString()) }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsScreen(title: String,
                          imageLink: String?,
                          description: String?,
                          author: String?,
                          source: String?,
                          navController: NavController){
    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        FloatingActionButton(modifier = Modifier
                            .padding(10.dp)
                            .size(40.dp), onClick = { navController.navigate("HomeUI")}) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Menu")
                        }
                    },
                    title = {Text("News")},
                )
            },
            content = { paddingValues ->
                // Your content here. Make sure to pass the paddingValues to prevent overlap.
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Top// Center content for this example
                ) {


                    Text(text = "${decodeStr(title)}", modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp), fontSize = 25.sp, fontFamily = FontFamily.Serif)

                    Card (modifier = Modifier.padding(20.dp), elevation = CardDefaults.cardElevation(50.dp)){
                        AsyncImage(model = imageLink, contentDescription = null)
                    }
                    Text(text = "${decodeStr(description)}", modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp), fontSize = 20.sp, fontFamily = FontFamily.Monospace)

                    Text(text = "Source: ${decodeStr(source)}", modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp), fontSize = 20.sp, fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold)

                    Box(modifier = Modifier.align(Alignment.End)){
                    Text(text = "Author: ${decodeStr(author)}", modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp), fontSize = 25.sp, fontFamily = FontFamily.Serif)

                    }

                }
            }
        )
    }
}

@Composable
fun NavigationGraph(viewModel: MyViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "HomeUI"){
        composable(route = "HomeUI") {
            HomeUI(viewModel = viewModel, navController = navController)
        }

        composable(route = "details_screen/{title}/{imageLink}/{description}/{author}/{source}"){
            val title = it.arguments?.getString("title")
            val imageLink = it.arguments?.getString("imageLink")
            val description = it.arguments?.getString("description")
            val author = it.arguments?.getString("author")
            val source = it.arguments?.getString("source")

            DetailsScreen(
                title = title!!,
                imageLink = imageLink,
                description = description,
                author = author,
                source = source,
                navController = navController
            )
        }
    }
}