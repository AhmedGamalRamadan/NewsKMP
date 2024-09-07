package com.ag.project.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ag.project.presentation.components.ShimmerListItem
import com.ag.project.presentation.components.EditTextSearch
import com.ag.project.presentation.components.NewsItem
import com.ag.project.presentation.screen.NewsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NewsHomeScreen() {

    val viewModel: NewsViewModel = koinViewModel()
    val newsState by viewModel.newsState.collectAsState()


    val categoryList =
        listOf(
            "General",
            "Business",
            "Technology",
            "Sports",
            "Science",
            "Health",
            "Entertainment"
        )


    var categorySelectedIndex by remember { mutableIntStateOf(0) }

    Column {

        EditTextSearch()

        LazyRow {
            itemsIndexed(categoryList) { index, category ->
                val isSelected = index == categorySelectedIndex

                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = {
                        categorySelectedIndex = index
                        viewModel.getNewsByCategory(category)
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = if (isSelected) Color.White else Color.Black,
                        backgroundColor = if (isSelected) Color.Blue else Color.White
                    ),
                    elevation = ButtonDefaults.elevation(
                        pressedElevation = 13.dp,
                        defaultElevation = 2.dp
                    )
                ) {
                    Text(text = category)
                }
            }
        }

        Spacer(Modifier.height(8.dp))

        if (newsState.isEmpty()) {
            LazyColumn {
                items(6) {
                    ShimmerListItem(true)

                }
            }
        } else {
            ShimmerListItem(false)

            LazyColumn {
                items(newsState) { article ->
                    NewsItem(article = article)
                }
            }
        }
    }
}