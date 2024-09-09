package com.ag.project.presentation.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ag.project.domain.model.Article
import com.ag.project.utils.Screen
import com.ag.project.utils.encodeUrl
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
@Composable
fun NewsItem(
    article: Article,
    navHostController: NavHostController
) {

    val image = article.urlToImage?.let {
        encodeUrl(it)
    }?: ""

    var isFavorite by remember {
        mutableStateOf(false)
    }

    val favoriteIcon: ImageVector = if (isFavorite) {
        Icons.Default.Favorite
    } else {
        Icons.Default.FavoriteBorder
    }


    Card(
        shape = RoundedCornerShape(17.dp),
        elevation = 12.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .background(Color.White)
            .clickable {
                navHostController.navigate(Screen.Details.rout + "/${image}/${article.title}/${article.description}")
            }

    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            article.urlToImage?.let { imageUrl ->
                CoilImage(
                    imageModel = { imageUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                    ),
                    loading = {
                        Box(
                            modifier = Modifier.matchParentSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()

                        }
                    },
                    failure = {
                        Text("Image Not Supported")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }


            article.source?.name?.let { sourceName ->
                Text(
                    text = sourceName,
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

            article.title?.let { title ->
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(6.dp)
                )
            }


            article.publishedAt?.let { publishedAt ->
                Text(
                    text = publishedAt,
                    color = Color.Black,
                    fontSize = 15.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(10.dp)
                )
            }

            Box(modifier = Modifier
                .padding(10.dp)
                .clickable {
                    isFavorite = !isFavorite
                }
            ) {
                Icon(
                    imageVector = favoriteIcon,
                    contentDescription = "Favorite",
                )
            }

        }
    }
}
