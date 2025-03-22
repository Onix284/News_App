package com.example.newsapp.Screens
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.newsapp.MyViewModel.MyViewModel


@Composable
fun HomeUI(viewModel: MyViewModel) {

    val response = viewModel.response.value?.articles ?: emptyList()
    Log.d("My_Tag", "HomeUI: ${viewModel.response.value}")


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                items(response){
                    NewsPrefab(
                        modifier = Modifier,
                        title = it.title!!,
                        imageLink = it.urlToImage
                    )

                }
            }
        }
}


@Composable
fun NewsPrefab(modifier: Modifier, title: String, imageLink: String?){
    Card (modifier = Modifier
        .padding(10.dp)
        .height(200.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(20.dp)
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